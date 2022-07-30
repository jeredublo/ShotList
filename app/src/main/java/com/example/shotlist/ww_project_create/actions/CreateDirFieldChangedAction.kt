package com.example.shotlist.ww_project_create.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState
import com.example.shotlist.ww_project_create.updaters.CPDirectorUpdater

class CreateDirFieldChangedAction(private val newDir : String) : BaseAction<CreateProjectState> {

    override suspend fun performAction(currentState: CreateProjectState, sendUpdate: (BaseUpdater<CreateProjectState>) -> Unit, sendEvent: (BaseEvent) -> Unit) {
        // Director field changed.. send update
        sendUpdate(CPDirectorUpdater(newDir))
    }

}