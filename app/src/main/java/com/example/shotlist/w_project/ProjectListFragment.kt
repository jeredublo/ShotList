package com.example.shotlist.w_project

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.base_mvi.MVIFragment
import com.example.shotlist.repository.SLDatabase
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.actions.AddButtonClickedAction
import com.example.shotlist.w_project.actions.FilterClickedAction
import com.example.shotlist.w_project.actions.InitProjectsAction
import com.example.shotlist.w_project.actions.ProjectClickedAction
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.list.ProjectAdapter
import kotlinx.coroutines.Dispatchers

class ProjectListFragment : MVIFragment<ProjectListState, ProjectListViewModel>(ProjectListViewModel::class.java, R.layout.projectList_fragment)
{


    override fun getInitAction(): BaseAction<ProjectListState>? {
        return context?.let {
            val dao = SLDatabase.getDatabase(it).slDao()
            val repo = SLRepository(dao, Dispatchers.IO)
            InitProjectsAction(repo)
        }
    }


    override fun initUI(view: View) {
        // initialize the adapter and click listeners

        // Filter Listener
        view.findViewById<TextView>(R.id.filter_text).setOnClickListener {
            FilterClickedAction()
        }

        // Recycler view listener
        view.findViewById<RecyclerView>(R.id.projectList_list).run {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProjectAdapter(listOf()) {
                viewModel.performAction(ProjectClickedAction(it.projectId))}  // passing in the onclick
        }

        // Add project button listener
        view.findViewById<TextView>(R.id.add_project_button).setOnClickListener {
            AddButtonClickedAction()
        }

    }


    override fun renderUI(newState: ProjectListState) {


        // things that dont change:
        // title needs to be set
        // filter needs to be set
        // thats it.

        when (newState.projectList) {
            is DataResult.Success -> {
                // change filter?



                if (newState.projectList.data.isEmpty()) {
                    view?.run {
//                    there are no projects found
//                    only show add project button &
//                    maybe a message saying 0 projects found
//                    loading spinner needs to stop
//                        dont show error message


                    }
                 }
                 else {
                     view?.run {

                     }
                 }
            }
            is DataResult.Error -> {
                view?.run {
//                    loading spinnner needs to stop
//                    log error message to console
//                    show user-friendly error message on screen

                }
            }
            is DataResult.Loading -> {
                view?.run {
                    //loading spinner needs to spin


                }
            }
        }
    }
}