package com.example.shotlist.w_project

import android.view.View
import android.widget.Filter
import android.widget.ImageView
import android.widget.ProgressBar
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
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.Dispatchers

class ProjectListFragment : MVIFragment<ProjectListState, ProjectListViewModel>(ProjectListViewModel::class.java, R.layout.projectlist_fragment)
{

    lateinit var adapter: ProjectAdapter

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
            viewModel.performAction(FilterClickedAction())
        }

        // Recycler view listener
        adapter = ProjectAdapter() {
            viewModel.performAction(ProjectClickedAction(it.projectId))
        }
        view.findViewById<RecyclerView>(R.id.projectList_list).run {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapter
        }

        // Add project button listener
        view.findViewById<TextView>(R.id.add_project_button).setOnClickListener {
            viewModel.performAction(AddButtonClickedAction())
        }

    }


    override fun renderUI(newState: ProjectListState) {
        view?.run {
            findViewById<TextView>(R.id.filter_text)?.text = newState.sortBy.title
            findViewById<ImageView>(R.id.filter_icon)?.setImageResource(newState.sortBy.icon)
        }

        when (newState.projectList) {

            is DataResult.Loading -> {
                view?.run {
                    //loading spinner needs to spin
                    findViewById<CircularProgressIndicator>(R.id.loading_spinner)?.visibility = View.VISIBLE
                    findViewById<RecyclerView>(R.id.projectList_list)?.visibility = View.GONE
                    findViewById<TextView>(R.id.error_message)?.visibility = View.GONE
                    findViewById<TextView>(R.id.add_project_button)?.visibility = View.VISIBLE

                }
            }

            is DataResult.Success -> {
                view?.run {
                    findViewById<CircularProgressIndicator>(R.id.loading_spinner)?.visibility = View.GONE

                }

                if (newState.projectList.data.isEmpty()) {
                    view?.run {
                        findViewById<RecyclerView>(R.id.projectList_list)?.visibility = View.GONE
                        findViewById<TextView>(R.id.error_message)?.text = "No projects found"
                        findViewById<TextView>(R.id.add_project_button)?.visibility = View.VISIBLE

                    }
                 }
                 else {
                     view?.run {
                         findViewById<RecyclerView>(R.id.projectList_list).run {
                             visibility = View.VISIBLE
                         }
                         findViewById<TextView>(R.id.error_message)?.visibility = View.GONE
                         findViewById<TextView>(R.id.add_project_button)?.visibility = View.VISIBLE

                     }
                    adapter.submitList(newState.projectList.data) // TODO: update the templates to use diffutil now
                 }
            }
            is DataResult.Error -> {
                view?.run {
                    findViewById<CircularProgressIndicator>(R.id.loading_spinner)?.visibility = View.GONE
                    findViewById<TextView>(R.id.error_message)?.text = "Error loading your projects"
                    findViewById<RecyclerView>(R.id.projectList_list)?.visibility = View.GONE
                    findViewById<TextView>(R.id.add_project_button)?.visibility = View.GONE

                }
            }
        }
    }
}