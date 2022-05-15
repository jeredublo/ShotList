package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.data_structs.SortedBy
import com.example.shotlist.w_project.updaters.FilterSortUpdater

// Called when user clicks on filter
class FilterClickedAction() : BaseAction<ProjectListState> {
    override suspend fun performAction(currentState: ProjectListState,
        sendUpdate: (BaseUpdater<ProjectListState>) -> Unit,
        sendEvent: (BaseEvent) -> Unit)  {

        // need to cycle thru the filter variable and change how we are filtering the list of projects
        // then update state with it
        val newSort = when(currentState.sortBy) {
            SortedBy.NameAsc -> SortedBy.NameDesc
            SortedBy.NameDesc -> SortedBy.DateAsc
            SortedBy.DateAsc -> SortedBy.DateDesc
            SortedBy.DateDesc -> SortedBy.NameAsc
        }
        sendUpdate(FilterSortUpdater(newSortBy = newSort))
    }
}