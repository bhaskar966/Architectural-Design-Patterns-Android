package com.bhaskar.architectural_design_patterns_android.data.remote

import com.bhaskar.architectural_design_patterns_android.data.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String = "API_KEY",
        @Query("q") query: String,
    ): PixabayResponse


}