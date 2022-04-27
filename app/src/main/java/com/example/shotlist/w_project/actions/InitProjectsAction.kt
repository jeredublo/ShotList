package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.ProjectListState

// import kotlinx.coroutines.flow.collect

class InitProjectsAction(repo : SLRepository) : BaseAction<ProjectListState> {
    override suspend fun performAction(currentState: ProjectListState, viewModel: MVIViewModel<ProjectListState>) {


        // TODO: resolve error for repo.getExamples(id).collect
        // by uncommenting the flow.collect import above.
    }
}