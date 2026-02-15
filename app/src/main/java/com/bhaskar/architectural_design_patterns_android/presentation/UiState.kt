package com.bhaskar.architectural_design_patterns_android.presentation

import com.bhaskar.architectural_design_patterns_android.domain.model.DomainModel

data class UiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<DomainModel>? = null
)
