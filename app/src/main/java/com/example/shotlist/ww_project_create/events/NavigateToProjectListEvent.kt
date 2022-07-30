package com.example.shotlist.ww_project_create.events

import android.content.Context
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.destinations.ProjectListScreenDestination
import com.example.shotlist.w_project.ProjectListScreen
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


class NavigateToProjectListEvent() : BaseEvent {
    override fun performEvent(context: Context, navigator: DestinationsNavigator) {
        navigator.navigate(ProjectListScreenDestination)
    }
}