package com.example.lessontest.presenter

internal interface PresenterContract {
    fun onAttach(stateViewActivity: Boolean)
    fun onDetach(stateViewActivity: Boolean)
}
