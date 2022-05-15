package com.example.shotlist.ww_project_create.actions
//
//import com.example.shotlist.base_mvi.BaseAction
//import com.example.shotlist.base_mvi.MVIViewModel
//import com.example.shotlist.repository.SLRepository
//import com.example.shotlist.ww_project_create.data_structs.CreateProjectState
//import com.example.shotlist.w_project.data_structs.Project
//import com.example.shotlist.w_project.updaters.ProjectListUpdater
//import com.example.shotlist.ww_project_create.events.NavigateToProjectListEvent
//import java.util.UUID


//class DoneButtonClickedAction(
//    private val repo : SLRepository, ) : BaseAction<CreateProjectState>
//{
//    override suspend fun performAction(currentState: CreateProjectState, viewModel: MVIViewModel<CreateProjectState>) {
//        // done button clicked on Create Project screen
//
//        // 1. create new project
//        val uniqueID = UUID.randomUUID().toString()
//        val project = Project(uniqueID,
//                              currentState.name,
//                              currentState.director,
//                              currentState.cinematographer,
//                              currentState.date
//                            )
//
//        // 2. add project to db
//        repo.addProject(project).collect{}
//
//        // 3. navigate back to the project screen (for now. eventually would go to the shotlist)
//        viewModel.sendEvent(NavigateToProjectListEvent())
//    }
//}