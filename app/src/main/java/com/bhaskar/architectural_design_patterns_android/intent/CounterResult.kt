package com.bhaskar.architectural_design_patterns_android.intent

sealed interface CounterResult {

    data class UpdatedCounter(val counter: Int) : CounterResult

}