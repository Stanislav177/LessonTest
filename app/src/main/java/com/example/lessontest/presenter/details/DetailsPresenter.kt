package com.example.lessontest.presenter.details

import com.example.lessontest.view.details.ViewDetailsContract


internal class DetailsPresenter internal constructor(
    private val viewContract: ViewDetailsContract,
    private var count: Int = 0,
) : PresenterDetailsContract {

    private var stateView: Boolean = false

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        when (stateView) {
            true -> {
                count++
                viewContract.setCount(count)
            }
            false -> {
                count++
            }
        }
    }

    override fun onDecrement() {

        when (stateView) {
            true -> {
                count--
                viewContract.setCount(count)
            }
            false -> {
                count--
            }
        }

    }

    override fun onAttach(stateViewActivity: Boolean) {
        stateView = stateViewActivity
    }

    override fun onDetach(stateViewActivity: Boolean) {
        stateView = stateViewActivity
    }
}
