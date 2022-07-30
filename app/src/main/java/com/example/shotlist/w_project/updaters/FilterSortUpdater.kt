package com.example.shotlist.w_project.updaters

import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.data_structs.SortedBy

class FilterSortUpdater(private val newSortBy : SortedBy) : BaseUpdater<ProjectListState> {
    override fun performUpdate(prevState: ProjectListState): ProjectListState {

        // applying filter to the list
        val dataResult =
        if (prevState.projectList is DataResult.Success) {
            val newList = when(newSortBy) {
                SortedBy.NameAsc -> prevState.projectList.data.sortedBy { it.name }
                SortedBy.NameDesc ->prevState.projectList.data.sortedByDescending { it.name }
                SortedBy.DateAsc -> prevState.projectList.data.sortedBy { it.date }
                SortedBy.DateDesc -> prevState.projectList.data.sortedByDescending { it.date }
            }
            DataResult.Success(newList)
        }
        else
            prevState.projectList

        return prevState.copy(sortBy = newSortBy, projectList = dataResult)
    }
}