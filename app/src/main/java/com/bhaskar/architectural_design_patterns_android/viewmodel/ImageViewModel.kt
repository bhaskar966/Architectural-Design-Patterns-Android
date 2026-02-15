package com.bhaskar.architectural_design_patterns_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaskar.architectural_design_patterns_android.model.remote.ImageRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class ImageViewModel: ViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")

    fun updateQuery(query: String) {
        _query.value = query
    }

    init {
        viewModelScope.launch {
            _query
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .debounce(500)
                .collectLatest { query ->
                    getImages(query)
                }
        }
    }


    private val repository: ImageRepository by lazy {
        ImageRepository()
    }


    fun getImages(query: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val response = repository.getImages(query)
            if(response.isSuccess) {
                _uiState.update {
                    it.copy(
                        data = response.getOrNull(),
                        isLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        error = response.exceptionOrNull()?.message.toString(),
                        isLoading = false
                    )
                }

            }
        }

    }

}