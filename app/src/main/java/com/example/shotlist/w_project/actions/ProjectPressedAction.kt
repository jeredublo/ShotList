package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.events.NavigateToCreateProjectEvent
import com.example.shotlist.w_project.events.NavigateToShotListEvent

class ProjectPressedAction(
    private val projectId : String, ) : BaseAction<ProjectListState> {

    override suspend fun performAction(currentState: ProjectListState,
        sendUpdate: (BaseUpdater<ProjectListState>) -> Unit,
        sendEvent: (BaseEvent) -> Unit)  {
        // evoke event to navigate to the Shotlist screen
        sendEvent(NavigateToShotListEvent(projectId))
    }

}