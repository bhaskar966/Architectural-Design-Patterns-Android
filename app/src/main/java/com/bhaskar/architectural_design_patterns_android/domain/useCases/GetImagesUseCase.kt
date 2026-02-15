package com.bhaskar.architectural_design_patterns_android.domain.useCases

import com.bhaskar.architectural_design_patterns_android.data.repository.ImagesRepositoryImpl
import com.bhaskar.architectural_design_patterns_android.domain.reposiory.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetImagesUseCase {

    private val repository: ImagesRepository by lazy { ImagesRepositoryImpl() }

    operator fun invoke(q: String) = flow {
        val response = repository.getImages(q)
        emit(response)
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)

}