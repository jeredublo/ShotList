package com.example.shotlist.w_project

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.base_mvi.MVIFragment
import com.example.shotlist.repository.SLDatabase
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.actions.InitProjectsAction
import com.example.shotlist.w_project.data_structs.ProjectListState
import kotlinx.coroutines.Dispatchers

class ProjectListFragment : MVIFragment<ProjectListState, ProjectListViewModel>(ProjectListViewModel::class.java, R.layout.projectList_fragment) {
    override fun getInitAction(): BaseAction<ProjectListState>? {
        context?.let {
            val dao = SLDatabase.getDatabase(it).slDao()
            val repo = SLRepository(dao, Dispatchers.IO)
            InitProjectsAction(repo)
        }
    }

    override fun initUI(view: View) {
        // initialize the adapter and click listeners
    }

    override fun renderUI(newState: ProjectListState) {
//        when (newState.projectList) {
//            is DataResult.Success -> {
//                view?.run {
//
//                    }
//                }
//            }
//            is DataResult.Error -> {
//                view?.run {
//
//                }
//            }
//            is DataResult.Loading -> {
//                view?.run {
//
//                }
//            }
//        }
    }
}