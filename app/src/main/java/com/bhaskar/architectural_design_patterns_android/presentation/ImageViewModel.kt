package com.bhaskar.architectural_design_patterns_android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaskar.architectural_design_patterns_android.domain.useCases.GetImagesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class ImageViewModel: ViewModel() {

    private val useCases: GetImagesUseCase by lazy { GetImagesUseCase() }

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")


    fun updateQuery(q: String) {
        _query.update { q }
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

    fun getImages(q: String) {
        useCases(q)
            .onStart {
                _uiState.update {
                    it.copy(isLoading = true)
                }
            }
            .onEach { result ->
                if(result.isSuccess) {
                    _uiState.update {
                        it.copy(
                            data = result.getOrNull(),
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            error = result.exceptionOrNull()?.message.toString(),
                            isLoading = false
                        )
                    }
                }
            }.catch {
                _uiState.update {
                    it.copy(
                        error = it.error,
                        isLoading = false
                    )
                }
            }.launchIn(viewModelScope)
    }

}