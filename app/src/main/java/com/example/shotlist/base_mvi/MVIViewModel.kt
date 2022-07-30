package com.example.shotlist.base_mvi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class MVIViewModel<MyState>(initialState: MyState) : ViewModel() {
//    private val _state: MutableLiveData<State> = MutableLiveData(initialState)
//    val state: LiveData<State> = _state
//    private val updateChannel = Channel<BaseUpdater<State>>(Channel.UNLIMITED)
//    val eventChannel = Channel<BaseEvent>(Channel.UNLIMITED)


    private val _state: MutableState<MyState> = mutableStateOf(initialState)
    val state: State<MyState> = _state
    private val updateFlow = MutableSharedFlow<BaseUpdater<MyState>>()
    private val _eventFlow = MutableSharedFlow<BaseEvent>()
    val eventFlow: SharedFlow<BaseEvent> = _eventFlow


    init {
//        viewModelScope.launch {
//            for (updater in updateChannel) {
//                state.value?.let { currState ->
//                    _state.value = updater.performUpdate(currState)
//                }
//            }
//        }
        viewModelScope.launch {
            updateFlow.collect { updater ->
                _state.value = updater.performUpdate(state.value)
            }
        }
    }

    fun sendUpdate(update: BaseUpdater<MyState>) {
        viewModelScope.launch {
            updateFlow.emit(update) // updateChannel.send(update)
        }
    }

    fun sendEvent(event: BaseEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

//    fun performAction(action: BaseAction<MyState>) {
//        viewModelScope.launch {
//            state.value?.let { currState ->
//                action.performAction(currState, this@MVIViewModel)
//            }
//        }
//    }

    val performAction : (BaseAction<MyState>) -> Unit = { action ->
        viewModelScope.launch {
            action.performAction(state.value, ::sendUpdate, ::sendEvent)
        }
    }
}