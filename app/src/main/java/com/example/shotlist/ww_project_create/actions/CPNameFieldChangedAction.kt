package com.example.shotlist.ww_project_create.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.updaters.FilterSortUpdater
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState
import com.example.shotlist.ww_project_create.updaters.CPNameUpdater

class CPNameFieldChangedAction(private val newName: String) : BaseAction<CreateProjectState> {

    override suspend fun performAction(currentState: CreateProjectState, sendUpdate: (BaseUpdater<CreateProjectState>) -> Unit, sendEvent: (BaseEvent) -> Unit) {
        // fields changed... need to update the state
        sendUpdate(CPNameUpdater(newName))
    }

}