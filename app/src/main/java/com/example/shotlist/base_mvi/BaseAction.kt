package com.example.shotlist.base_mvi

interface BaseAction<MyState> {
    suspend fun performAction(
        currentState: MyState,
        sendUpdate: (BaseUpdater<MyState>) -> Unit,
        sendEvent: (BaseEvent) -> Unit
//        viewModel: MVIViewModel<State>
    )
}