package com.example.shotlist.w_project.updaters

import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.data_structs.Project

class ProjectListUpdater(private val newProjectList: DataResult<List<Project>>) : BaseUpdater<ProjectListState> {
    override fun performUpdate(prevState: ProjectListState): ProjectListState {
        return prevState.copy(projectList = newProjectList)
    }
}