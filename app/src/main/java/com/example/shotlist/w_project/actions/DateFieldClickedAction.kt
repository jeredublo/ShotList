package com.example.shotlist.w_project.actions

import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.data_structs.CreateProjectState
import com.example.shotlist.w_project.events.DatePickerDialogEvent
import com.example.shotlist.w_project.events.NavigateToAddProjectEvent
import com.google.android.material.datepicker.MaterialDatePicker


class DateFieldClickedAction() : BaseAction<CreateProjectState> {

    override suspend fun performAction(currentState: CreateProjectState, viewModel: MVIViewModel<CreateProjectState>) {
        viewModel.sendEvent(DatePickerDialogEvent())
    }

}