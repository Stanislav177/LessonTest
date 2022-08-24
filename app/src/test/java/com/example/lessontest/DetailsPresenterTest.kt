package com.example.lessontest

import com.example.lessontest.presenter.details.DetailsPresenter
import com.example.lessontest.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        presenter = DetailsPresenter(viewContract, 0)
    }

    @Test
    fun setOnIncrement_Test() {
        val i = 1
        presenter.stateView = true
        presenter.onIncrement()
        verify(viewContract, times(1)).setCount(i)
    }

    @Test
    fun setOnDecrement_Test() {
        val i = -1
        presenter.stateView = true
        presenter.onDecrement()
        verify(viewContract, times(1)).setCount(i)
    }

    @Test
    fun setOnAttach_Test() {
        presenter.onAttach()
        assertTrue(presenter.stateView)
    }

    @Test
    fun setOnDetach_Test() {
        presenter.onDetach()
        assertFalse(presenter.stateView)
    }
}