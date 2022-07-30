package com.example.shotlist.w_project.updaters

import android.provider.ContactsContract
import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.data_structs.Project
import com.example.shotlist.w_project.data_structs.SortedBy

class ProjectListUpdater(private val newProjectList: DataResult<List<Project>>) : BaseUpdater<ProjectListState> {
    override fun performUpdate(prevState: ProjectListState): ProjectListState {

        // applying filter to the list
        val dataResult =
            if (newProjectList is DataResult.Success) {
                val sortedList = when(prevState.sortBy) {
                    SortedBy.NameAsc -> newProjectList.data.sortedBy { it.name }
                    SortedBy.NameDesc ->newProjectList.data.sortedByDescending { it.name }
                    SortedBy.DateAsc -> newProjectList.data.sortedBy { it.date }
                    SortedBy.DateDesc -> newProjectList.data.sortedByDescending { it.date }
                }
                DataResult.Success(sortedList)
            }
            else
                newProjectList

        return prevState.copy(projectList = dataResult)
    }
}