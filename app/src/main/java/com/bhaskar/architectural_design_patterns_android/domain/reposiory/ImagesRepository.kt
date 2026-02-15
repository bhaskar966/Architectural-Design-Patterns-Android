package com.bhaskar.architectural_design_patterns_android.domain.reposiory

import com.bhaskar.architectural_design_patterns_android.domain.model.DomainModel

interface ImagesRepository {

    suspend fun getImages(q: String): Result<List<DomainModel>>

}