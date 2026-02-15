package com.bhaskar.architectural_design_patterns_android.presenter

import com.bhaskar.architectural_design_patterns_android.CounterContract

class PresenterImpl(
    private val model: CounterContract.Model
): CounterContract.Presenter {

    private var view: CounterContract.View? = null

    override fun attach(view: CounterContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun onIncrementCounter() {
        model.incrementCounter()
        view?.showCounter(model.getCounter())
    }

    override fun onDecrementCounter() {
        model.decrementCounter()
        view?.showCounter(model.getCounter())
    }
}