package com.bhaskar.architectural_design_patterns_android.model

class CounterModelImpl: CounterModel {

    private var counter: Int = 0

    override fun increment() {
        counter++
    }

    override fun decrement() {
        counter--
    }

    override fun getCounter(): Int {
        return counter
    }
}