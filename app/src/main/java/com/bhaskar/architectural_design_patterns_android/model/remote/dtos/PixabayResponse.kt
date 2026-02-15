package com.bhaskar.architectural_design_patterns_android.model.remote.dtos

data class PixabayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)