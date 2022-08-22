package com.example.lessontest.view.search

import com.example.lessontest.model.SearchResult
import com.example.lessontest.view.ViewContract


internal interface ViewSearchContract : ViewContract {
    fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    )

    fun displayError()
    fun displayError(error: String)
    fun displayLoading(show: Boolean)
}
