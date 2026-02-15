package com.bhaskar.architectural_design_patterns_android.intent

sealed interface CounterAction {

    data object IncrementClicked: CounterAction
    data object DecrementClicked: CounterAction

}