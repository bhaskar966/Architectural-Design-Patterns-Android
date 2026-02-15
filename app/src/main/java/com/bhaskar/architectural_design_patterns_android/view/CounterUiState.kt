package com.bhaskar.architectural_design_patterns_android.view

import androidx.compose.runtime.Immutable

@Immutable
data class CounterUiState(
    val counter: Int = 0
)
