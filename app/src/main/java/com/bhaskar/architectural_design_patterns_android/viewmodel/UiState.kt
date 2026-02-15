package com.bhaskar.architectural_design_patterns_android.viewmodel

import com.bhaskar.architectural_design_patterns_android.model.remote.dtos.Hit

data class UiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Hit>? = null
)
