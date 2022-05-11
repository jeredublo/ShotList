package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.data_structs.ProjectListState

class ProjectClickedAction(
    val projectId : String, ) : BaseAction<ProjectListState> {

    override suspend fun performAction(currentState: ProjectListState, viewModel: MVIViewModel<ProjectListState>) {
        // evoke event to navigate to the Shotlist screen
    }

}