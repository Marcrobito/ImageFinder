package io.hannah.imagefinder.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

fun <STATE, EVENT> ViewModel.stateReducerFlow(
    initialState: STATE,
    reduceState: (STATE, EVENT) -> STATE,
): StateReducerFlow<STATE, EVENT> = StateReducerFlowImpl(initialState, reduceState, viewModelScope)