package com.example.shotlist.w_project.events

import android.content.Context
import com.example.shotlist.base_mvi.BaseEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class NavigateToCreateProjectEvent(/*add your extra stuff for toastsetc here*/) : BaseEvent {

    override fun performEvent(context: Context, navigator: DestinationsNavigator) {
//        navigator.navigate()    // context for dialogs and toasts and stuff that need it
    }

}