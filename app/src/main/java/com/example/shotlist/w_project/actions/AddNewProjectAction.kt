package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.data_structs.Project
import com.example.shotlist.w_project.updaters.ProjectListUpdater
import java.util.Date
import java.util.UUID

 import kotlinx.coroutines.flow.collect

class AddNewProjectAction(
    private val repo : SLRepository,
    private val name : String,
    private val director : String,
    private val cinematographer : String,
    private val date : Date,
) : BaseAction<ProjectListState> {

    override suspend fun performAction(currentState: ProjectListState, viewModel: MVIViewModel<ProjectListState>) {

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