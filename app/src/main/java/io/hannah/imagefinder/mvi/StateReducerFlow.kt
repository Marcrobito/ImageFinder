package io.hannah.imagefinder.mvi

import kotlinx.coroutines.flow.StateFlow

interface StateReducerFlow<STATE, EVENT> : StateFlow<STATE> {
    fun handleEvent(event: EVENT)
}