package com.example.lessontest.presenter.search

import com.example.lessontest.presenter.PresenterContract


internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
}
