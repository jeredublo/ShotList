package com.example.shotlist.base_mvi

interface BaseAction<State> {
    suspend fun performAction(
        currentState: State,
        viewModel: MVIViewModel<State>
    )
}