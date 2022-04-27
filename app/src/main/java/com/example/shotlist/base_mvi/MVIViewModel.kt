package com.example.shotlist.base_mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

abstract class MVIViewModel<State>(initialState: State) : ViewModel() {
    private val _state: MutableLiveData<State> = MutableLiveData(initialState)
    val state: LiveData<State> = _state
    private val updateChannel = Channel<BaseUpdater<State>>(Channel.UNLIMITED)
    val eventChannel = Channel<BaseEvent>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            for (updater in updateChannel) {
                state.value?.let { currState ->
                    _state.value = updater.performUpdate(currState)
                }
            }
        }
    }

    fun sendUpdate(update: BaseUpdater<State>) {
        viewModelScope.launch {
            updateChannel.send(update)
        }
    }

    fun sendEvent(event: BaseEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    fun performAction(action: BaseAction<State>) {
        viewModelScope.launch {
            state.value?.let { currState ->
                action.performAction(currState, this@MVIViewModel)
            }
        }
    }
}