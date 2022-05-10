package com.example.shotlist.w_project.updaters

import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.data_structs.SortedBy

class FilterSortUpdater(private val newSortBy : SortedBy) : BaseUpdater<ProjectListState> {
    override fun performUpdate(prevState: ProjectListState): ProjectListState {
         return prevState.copy(sortBy = newSortBy)

    }
}