package com.example.shotlist.w_project

import android.view.View
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIFragment
import com.example.shotlist.repository.SLDao
import com.example.shotlist.repository.SLDatabase
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.actions.InitProjectsAction
import kotlinx.coroutines.Dispatchers

class ProjectListFragment : MVIFragment<ProjectListState, ProjectListViewModel>(ProjectListViewModel::class.java, R.layout.projectList_fragment) {
    override fun getInitAction(): BaseAction<ProjectListState>? {
        context?.let {
            val dao = SLDatabase.getDatabase(it).slDao()
            val repo = SLRepository(dao, Dispatchers.IO)
            return InitProjectsAction(repo)
        }
    }

    override fun initUI(view: View) {
        TODO("Not yet implemented")
    }

    override fun renderUI(newState: ProjectListState) {
        TODO("Not yet implemented")
    }
}