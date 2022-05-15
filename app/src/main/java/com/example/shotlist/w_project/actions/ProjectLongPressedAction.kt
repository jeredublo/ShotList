package com.example.shotlist.w_project.actions

import android.system.Os.accept
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.updaters.ProjectListUpdater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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