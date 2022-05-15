package com.example.shotlist.base_mvi

interface BaseUpdater<MyState> {
    fun performUpdate(prevState: MyState): MyState
}