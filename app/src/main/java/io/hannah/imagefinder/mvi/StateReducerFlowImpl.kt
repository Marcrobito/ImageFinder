package io.hannah.imagefinder.mvi

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn

class StateReducerFlowImpl<STATE, EVENT>(
    initialState: STATE,
    reduceState: (STATE, EVENT) -> STATE,
    scope: CoroutineScope
) : StateReducerFlow<STATE, EVENT> {

    private val events = Channel<EVENT>(Channel.UNLIMITED)

    private val stateFlow = events
        .receiveAsFlow()
        .runningFold(initialState, reduceState)
        .stateIn(scope, SharingStarted.Eagerly, initialState)
        .also { it.launchIn(scope) }

    override val replayCache get() = stateFlow.replayCache

    override val value get() = stateFlow.value

    override suspend fun collect(collector: FlowCollector<STATE>): Nothing {
        stateFlow.collect(collector)
    }

    override fun handleEvent(event: EVENT) {
        val result = events.trySend(event)
        if (result.isFailure) {
            Log.w("Dial H", "Failed to send event: ${result.exceptionOrNull()?.message}")
        }
    }

}