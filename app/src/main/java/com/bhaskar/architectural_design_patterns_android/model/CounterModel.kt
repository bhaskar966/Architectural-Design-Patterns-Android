package com.bhaskar.architectural_design_patterns_android.model

class CounterModel {

    private var counter = 0

    fun increment() {
        counter++
    }

    fun decrement() {
        counter--
    }

    fun getCounter() = counter

}