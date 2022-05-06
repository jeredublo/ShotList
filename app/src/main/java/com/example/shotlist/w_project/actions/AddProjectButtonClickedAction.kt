package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.events.NavigateToAddProjectEvent

class AddProjectButtonClickedAction() : BaseAction<ProjectListState> {

    override suspend fun performAction(currentState: ProjectListState, viewModel: MVIViewModel<ProjectListState>) {
        // need to evoke event to change screens
        viewModel.sendEvent(NavigateToAddProjectEvent())
    }

}