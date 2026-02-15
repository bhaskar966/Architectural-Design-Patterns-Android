package com.bhaskar.architectural_design_patterns_android.model.remote

import com.bhaskar.architectural_design_patterns_android.model.remote.dtos.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String = "API_KEY_HERE",
        @Query("q") query: String,
    ): PixabayResponse


}