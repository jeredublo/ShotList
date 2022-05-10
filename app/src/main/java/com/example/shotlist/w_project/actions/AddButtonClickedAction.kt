package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.events.NavigateToAddProjectEvent

// To evoke screen transition into creating a project.
// Called when the user clicks on the Add Project Button on the project list screen
class AddButtonClickedAction() : BaseAction<ProjectListState> {

    override suspend fun performAction(currentState: ProjectListState, viewModel: MVIViewModel<ProjectListState>) {
        // need to evoke event to change screens
        viewModel.sendEvent(NavigateToAddProjectEvent())
    }

}