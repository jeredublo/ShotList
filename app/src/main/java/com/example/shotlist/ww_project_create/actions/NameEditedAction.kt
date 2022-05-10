package com.example.shotlist.ww_project_create.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState

// import kotlinx.coroutines.flow.collect

class NameEditedAction() : BaseAction<CreateProjectState> {
    override suspend fun performAction(currentState: CreateProjectState, viewModel: MVIViewModel<CreateProjectState>) {
        TODO("Not yet implemented")

        // TODO: resolve error for repo.getExamples(id).collect
        // by uncommenting the flow.collect import above.
    }
}