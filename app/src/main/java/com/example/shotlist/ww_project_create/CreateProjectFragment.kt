package com.example.shotlist.ww_project_create

import android.view.View
import android.widget.Button
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIFragment
import com.example.shotlist.ww_project_create.actions.DoneButtonClickedAction
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState

class CreateProjectFragment : MVIFragment<CreateProjectState, CreateProjectViewModel>(CreateProjectViewModel::class.java, R.layout.createproject_fragment) {
    override fun getInitAction(): BaseAction<CreateProjectState>? {
        return null // nothing to set up for this screen
    }

    override fun initUI(view: View) {
        // attach the text input fields
        // attach click listener for the Done button
        view.findViewById<Button>(R.id.done_button).setOnClickListener{
            viewModel.performAction(DoneButtonClickedAction())
        }
    }

    override fun renderUI(newState: CreateProjectState) {
        // no loading/success/fail for this

    }
}