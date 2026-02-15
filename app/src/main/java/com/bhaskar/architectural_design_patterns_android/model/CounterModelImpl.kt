package com.bhaskar.architectural_design_patterns_android.model

import com.bhaskar.architectural_design_patterns_android.CounterContract

class CounterModelImpl: CounterContract.Model {

    private var counter = 0

    override fun incrementCounter() {
        counter++
    }

    override fun decrementCounter() {
        counter--
    }

    override fun getCounter(): Int = counter
}