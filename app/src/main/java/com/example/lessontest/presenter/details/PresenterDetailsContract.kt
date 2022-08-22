package com.example.lessontest.presenter.details

import com.example.lessontest.presenter.PresenterContract


internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}
