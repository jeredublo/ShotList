package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.updaters.ProjectListUpdater
import kotlinx.coroutines.flow.collect


class ProjectLongPressedAction(
    private val repo : SLRepository,
    private val projectId: String,
) : BaseAction<ProjectListState>
{
    override suspend fun performAction(currentState: ProjectListState, viewModel: MVIViewModel<ProjectListState>) {
        // delete the project
        repo.deleteProject(projectId).collect {
            projects -> viewModel.sendUpdate(ProjectListUpdater(projects))
        }


    }
}