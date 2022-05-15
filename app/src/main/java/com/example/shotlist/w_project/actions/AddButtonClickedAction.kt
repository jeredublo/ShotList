package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.events.NavigateToCreateProjectEvent

// To evoke screen transition into creating a project.
// Called when the user clicks on the Add Project Button on the project list screen
class AddButtonClickedAction() : BaseAction<ProjectListState> {

    override suspend fun performAction(currentState: ProjectListState,
        sendUpdate: (BaseUpdater<ProjectListState>) -> Unit,
        sendEvent: (BaseEvent) -> Unit) {
        // need to evoke event to change screens
        sendEvent(NavigateToCreateProjectEvent())
    }

}