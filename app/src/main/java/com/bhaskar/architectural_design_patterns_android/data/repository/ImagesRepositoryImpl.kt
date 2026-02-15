package com.bhaskar.architectural_design_patterns_android.data.repository

import com.bhaskar.architectural_design_patterns_android.data.remote.RetrofitInstance
import com.bhaskar.architectural_design_patterns_android.domain.model.DomainModel
import com.bhaskar.architectural_design_patterns_android.domain.reposiory.ImagesRepository

class ImagesRepositoryImpl: ImagesRepository {

    private val apiService by lazy { RetrofitInstance.getApiService() }

    override suspend fun getImages(q: String): Result<List<DomainModel>> {
        return try {
            val response = apiService.getImages(query = q)
            val domainModelList =  response.hits.map {
                DomainModel(imageUrl = it.largeImageURL)
            }
            Result.success(domainModelList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}