package com.example.shotlist.ww_project_create.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState
import com.example.shotlist.w_project.data_structs.Project
import com.example.shotlist.w_project.updaters.ProjectListUpdater
import java.util.UUID


class DoneButtonClickedAction(
    private val repo : SLRepository,
    private val name : String,
    private val director : String,
    private val cinematographer : String,
    private val date : String,
) : BaseAction<CreateProjectState> {
    override suspend fun performAction(currentState: CreateProjectState, viewModel: MVIViewModel<CreateProjectState>) {
        // done button clicked on Create Project screen
        // need to call add new project action

        // 1. create new project
        val uniqueID = UUID.randomUUID().toString()
        val project = Project(uniqueID,
                              name,
                              director,
                              cinematographer,
                              date)
        // 2. add project to db
        repo.addProject(project).collect {
            // 3. send update to our state
            projects -> viewModel.sendUpdate(ProjectListUpdater(projects))
        }
    }
}