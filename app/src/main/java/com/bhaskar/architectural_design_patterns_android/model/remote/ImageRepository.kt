package com.bhaskar.architectural_design_patterns_android.model.remote

import com.bhaskar.architectural_design_patterns_android.model.remote.dtos.Hit

class ImageRepository {

    private val apiService by lazy { RetrofitInstance.getApiService() }

    suspend fun getImages(query: String): Result<List<Hit>> {

        return try {
            val response = apiService.getImages(query = query)
            Result.success(response.hits)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }

    }

}