package com.bhaskar.architectural_design_patterns_android.presenter

import com.bhaskar.architectural_design_patterns_android.model.CounterModelImpl

object PresenterFactory {

    fun provide() = PresenterImpl(model = CounterModelImpl())

}