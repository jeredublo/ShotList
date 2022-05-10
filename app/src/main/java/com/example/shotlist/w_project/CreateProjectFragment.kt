package com.example.shotlist.w_project

import android.view.View
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIFragment
import com.example.shotlist.w_project.data_structs.CreateProjectState

class CreateProjectFragment : MVIFragment<CreateProjectState, CreateProjectViewModel>(CreateProjectViewModel::class.java, R.layout.createproject_fragment) {
    override fun getInitAction(): BaseAction<CreateProjectState>? {
        return null // nothing to set up for this screen
    }

    override fun initUI(view: View) {
    }

    override fun renderUI(createState: CreateProjectState) {
    }
}