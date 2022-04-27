package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.updaters.ProjectListUpdater
import kotlinx.coroutines.flow.collect

// import kotlinx.coroutines.flow.collect

class InitProjectsAction(
    private val repo : SLRepository) : BaseAction<ProjectListState>
{
    override suspend fun performAction(currentState: ProjectListState, viewModel: MVIViewModel<ProjectListState>) {
        repo.getAllProjects().collect {
            projects -> viewModel.sendUpdate(ProjectListUpdater(projects))
        }
    }
}