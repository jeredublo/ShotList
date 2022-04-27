package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.ProjectListState
import com.example.shotlist.w_project.data_structs.Project
import java.util.Date

// import kotlinx.coroutines.flow.collect

class AddProjectAction(
    private val repo : SLRepository,
    private val name : String,
    private val director : String,
    private val cinematographer : String,
    private val date : Date,
) : BaseAction<ProjectListState> {

    override suspend fun performAction(currentState: ProjectListState, viewModel: MVIViewModel<ProjectListState>) {
        // by uncommenting the flow.collect import above.

        // create new project object
        //generate some KEY here

        val genId = "asdfghj"
        val project = Project(genId, name, director, cinematographer, date)
        // add to db? or maybe its not this action's responsibility
        repo.addProject(project)
        // ProjectListState = list<project>
        //send out a new state to an updater
    }

}