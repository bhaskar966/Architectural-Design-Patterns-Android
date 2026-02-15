package com.bhaskar.architectural_design_patterns_android.intent

sealed interface CounterIntent {

    data object Increment: CounterIntent
    data object Decrement: CounterIntent

}