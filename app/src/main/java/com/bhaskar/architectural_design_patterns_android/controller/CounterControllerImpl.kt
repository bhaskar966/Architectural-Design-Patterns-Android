package com.bhaskar.architectural_design_patterns_android.controller

import com.bhaskar.architectural_design_patterns_android.model.CounterModel
import com.bhaskar.architectural_design_patterns_android.view.CounterView

class CounterControllerImpl(
    private val model: CounterModel,
    private val view: CounterView
): CounterController {
    override fun onIncrementClick() {
        model.increment()
        view.showText(model.getCounter())
    }

    override fun onDecrementClick() {
        model.decrement()
        view.showText(model.getCounter())
    }
}