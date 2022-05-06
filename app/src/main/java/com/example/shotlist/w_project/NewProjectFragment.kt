package com.example.shotlist.w_project

import android.view.View
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIFragment

class NewProjectFragment : MVIFragment<NewProjectState, NewProjectViewModel>(NewProjectViewModel::class.java, R.layout.NewProject_fragment) {
    override fun getInitAction(): BaseAction<NewProjectState>? {
        TODO("Not yet implemented")
    }

    override fun initUI(view: View) {
        TODO("Not yet implemented")
    }

    override fun renderUI(newState: NewProjectState) {
        TODO("Not yet implemented")
    }
}