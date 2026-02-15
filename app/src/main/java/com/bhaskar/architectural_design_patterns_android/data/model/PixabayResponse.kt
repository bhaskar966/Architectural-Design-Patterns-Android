package com.bhaskar.architectural_design_patterns_android.data.model

data class PixabayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)