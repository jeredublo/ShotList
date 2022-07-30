package com.example.shotlist.w_project.events

import android.content.Context
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.destinations.CreateProjectScreenDestination
import com.example.shotlist.destinations.ProjectListScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class NavigateToCreateProjectEvent : BaseEvent {

    override fun performEvent(context: Context, navigator: DestinationsNavigator) {
        navigator.navigate(CreateProjectScreenDestination)
    }

}