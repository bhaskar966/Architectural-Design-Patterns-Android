package com.bhaskar.architectural_design_patterns_android.model

interface CounterModel {

    fun increment()
    fun decrement()
    fun getCounter(): Int

}