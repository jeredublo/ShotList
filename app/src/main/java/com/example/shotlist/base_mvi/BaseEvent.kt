package com.example.shotlist.base_mvi

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface BaseEvent {
    fun performEvent(context: Context, navigator: DestinationsNavigator)
}