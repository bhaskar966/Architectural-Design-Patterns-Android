package com.bhaskar.architectural_design_patterns_android.view

import androidx.lifecycle.ViewModel
import com.bhaskar.architectural_design_patterns_android.intent.CounterAction
import com.bhaskar.architectural_design_patterns_android.intent.CounterIntent
import com.bhaskar.architectural_design_patterns_android.intent.CounterResult
import com.bhaskar.architectural_design_patterns_android.model.CounterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel: ViewModel() {

    private val counterModel = CounterModel()

    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(intent: CounterIntent) {
        when(intent) {
            CounterIntent.Decrement -> handleAction(CounterAction.DecrementClicked)
            CounterIntent.Increment -> handleAction(CounterAction.IncrementClicked)
        }
    }

    fun handleAction(action: CounterAction) {
        when(action) {
            CounterAction.DecrementClicked -> {
                counterModel.decrement()
                updateState(CounterResult.UpdatedCounter(counter = counterModel.getCounter()))
            }
            CounterAction.IncrementClicked -> {
                counterModel.increment()
                updateState(CounterResult.UpdatedCounter(counter = counterModel.getCounter()))
            }
        }
    }


    fun updateState(result: CounterResult) {
        when(result) {
            is CounterResult.UpdatedCounter -> {
                _uiState.update { it.copy(counter =  result.counter) }
            }
        }
    }


}