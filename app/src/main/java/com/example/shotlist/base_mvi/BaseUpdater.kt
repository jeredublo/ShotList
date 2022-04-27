package com.example.shotlist.base_mvi

interface BaseUpdater<State> {
    fun performUpdate(prevState: State): State
}