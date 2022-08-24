package com.example.lessontest.presenter.details

import com.example.lessontest.view.details.ViewDetailsContract


internal class DetailsPresenter internal constructor(
    private val viewContract: ViewDetailsContract,
    private var countClazz: Int = 0,
    var stateView: Boolean = false
) : PresenterDetailsContract {

    override fun setCounter(count: Int) {
        this.countClazz = count
    }

    override fun onIncrement() {
        when (stateView) {
            true -> {
                countClazz++
                viewContract.setCount(countClazz)
            }
            false -> {
                countClazz++
            }
        }
    }

    override fun onDecrement() {
        when (stateView) {
            true -> {
                countClazz--
                viewContract.setCount(countClazz)
            }
            false -> {
                countClazz--
            }
        }
    }

    override fun onAttach() {
        this.stateView = true
    }

    override fun onDetach(){
        this.stateView = false
    }
}
