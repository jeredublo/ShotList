package com.example.shotlist.w_project.updaters

import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.w_project.ProjectListState
import com.example.shotlist.w_project.data_structs.Project

class ProjectListUpdater(private val newProjectList: List<Project>) : BaseUpdater<ProjectListState> {
    override fun performUpdate(prevState: ProjectListState): ProjectListState {
        return prevState.copy(projectList = newProjectList)
    }
}