package com.example.lessontest.presenter.search


import com.example.lessontest.model.SearchResponse
import com.example.lessontest.repository.GitHubRepository
import com.example.lessontest.view.search.ViewSearchContract
import retrofit2.Response

/**
 * В архитектуре MVP все запросы на получение данных адресуются в Репозиторий.
 * Запросы могут проходить через Interactor или UseCase, использовать источники
 * данных (DataSource), но суть от этого не меняется.
 * Непосредственно Презентер отвечает за управление потоками запросов и ответов,
 * выступая в роли регулировщика движения на перекрестке.
 */

internal class SearchPresenter internal constructor(
    private val viewContract: ViewSearchContract,
    private val repository: GitHubRepository,
) : PresenterSearchContract, GitHubRepository.GitHubRepositoryCallback {

    var stateView: Boolean = false

    override fun searchGitHub(searchQuery: String) {
        viewContract.displayLoading(true)
        repository.searchGithub(searchQuery, this)
    }

    override fun onAttach() {
        this.stateView = true
    }

    override fun onDetach() {
       this.stateView = false
    }

    override fun handleGitHubResponse(response: Response<SearchResponse?>?) {
        viewContract.displayLoading(false)
        if (stateView) {
            if (response != null && response.isSuccessful) {
                val searchResponse = response.body()
                val searchResults = searchResponse?.searchResults
                val totalCount = searchResponse?.totalCount
                if (searchResults != null && totalCount != null) {
                    viewContract.displaySearchResults(
                        searchResults,
                        totalCount
                    )
                } else {
                    viewContract.displayError("Search results or total count are null")
                }
            } else {
                viewContract.displayError("Response is null or unsuccessful")
            }
        }
    }

    override fun handleGitHubError() {
        if (stateView) {
            viewContract.displayLoading(false)
            viewContract.displayError()
        }
    }
}
