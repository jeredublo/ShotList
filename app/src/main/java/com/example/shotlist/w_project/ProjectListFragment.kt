package com.example.shotlist.w_project

import android.content.Context
import android.view.View
import android.widget.ImageView
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
import com.example.shotlist.w_project.actions.ProjectPressedAction
import com.example.shotlist.w_project.actions.ProjectLongPressedAction
import com.example.shotlist.w_project.data_structs.Project
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.w_project.list.ProjectAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.Dispatchers

class ProjectListFragment : MVIFragment<ProjectListState, ProjectListViewModel>(ProjectListViewModel::class.java, R.layout.projectlist_fragment)
{

    private lateinit var listAdapter: ProjectAdapter

    override fun getInitAction(): BaseAction<ProjectListState>? {
        return context?.let {
            val dao = SLDatabase.getDatabase(it).slDao()
            val repo = SLRepository(dao, Dispatchers.IO)
            InitProjectsAction(repo)
        }
    }

    override fun initUI(view: View) {
        // initialize the adapter and click listeners
        val repo = context?.let {
            val dao = SLDatabase.getDatabase(it).slDao()
            SLRepository(dao, Dispatchers.IO)
        }

        // Filter Listener
        view.findViewById<TextView>(R.id.filter_text).setOnClickListener {
            viewModel.performAction(FilterClickedAction())
        }

        // Recycler view listener
        listAdapter = ProjectAdapter(
            onClick = {viewModel.performAction(ProjectPressedAction(it.projectId))},
            onLongClick = { proj ->
                context?. let {
                    val repo = SLRepository(SLDatabase.getDatabase(it).slDao(), Dispatchers.IO)
                    // cont -> discardProjectDialog(context,proj)
                    viewModel.performAction(ProjectLongPressedAction(repo, proj.projectId))
                }
            }
        )
        view.findViewById<RecyclerView>(R.id.projectList_list).run {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
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

            // need to apply the sort filter to the list
        }
        when (newState.projectList) {

            is DataResult.Loading -> {
                view?.run {
                    findViewById<CircularProgressIndicator>(R.id.loading_spinner)?.visibility = View.VISIBLE
                    findViewById<RecyclerView>(R.id.projectList_list)?.visibility = View.GONE
                    findViewById<TextView>(R.id.error_message)?.visibility = View.GONE
                    findViewById<TextView>(R.id.project_count)?.visibility = View.GONE
                    findViewById<TextView>(R.id.add_project_button)?.visibility = View.VISIBLE
                }
            }

            is DataResult.Success -> {
                view?.findViewById<CircularProgressIndicator>(R.id.loading_spinner)?.visibility = View.GONE
                if (newState.projectList.data.isEmpty()) { // No projects found
                    view?.run {

                        findViewById<RecyclerView>(R.id.projectList_list)?.visibility = View.GONE
                        findViewById<TextView>(R.id.project_count).run {
                            visibility = View.VISIBLE
                            text = "No projects found"
                        }
                        findViewById<TextView>(R.id.error_message)?.visibility = View.GONE
                        findViewById<TextView>(R.id.add_project_button)?.visibility = View.VISIBLE
                    }
                 }
                 else {                                     // Projects found
                     view?.run {
                         findViewById<RecyclerView>(R.id.projectList_list)?.visibility = View.VISIBLE
                         findViewById<TextView>(R.id.project_count).run {
                             visibility = View.VISIBLE
                             text = "Projects found: ${newState.projectList.data.size}"
                         }
                         findViewById<TextView>(R.id.error_message)?.visibility = View.GONE
                         findViewById<TextView>(R.id.add_project_button)?.visibility = View.VISIBLE

                     }
                    listAdapter.submitList(newState.projectList.data) // TODO: update the templates to use diffutil now
                 }
            }
            is DataResult.Error -> {
                view?.run {
                    findViewById<CircularProgressIndicator>(R.id.loading_spinner)?.visibility = View.GONE
                    findViewById<TextView>(R.id.error_message)?.visibility = View.VISIBLE
                    findViewById<TextView>(R.id.error_message)?.text = "Error loading your projects"
                    findViewById<TextView>(R.id.project_count)?.visibility = View.GONE
                    findViewById<RecyclerView>(R.id.projectList_list)?.visibility = View.GONE
                    findViewById<TextView>(R.id.add_project_button)?.visibility = View.GONE

                }
            }
        }
    }
    private fun discardProjectDialog(context: Context, proj : Project, ) : ((Project) -> Unit)? {

        val repo = SLRepository(SLDatabase.getDatabase(context).slDao(), Dispatchers.IO)
        var function: ((Project) -> Unit)? = null

        // bring up the dialog
        MaterialAlertDialogBuilder(context)
            .setTitle("Delete project?")
            .setMessage("Do you want to delete your project '${proj.name}'?")
            .setNegativeButton("Cancel") { dialog, which ->
                function = { null }   // close dialog
            }
            .setPositiveButton("Confirm") { dialog, which ->
                function = { proj -> viewModel.performAction(ProjectLongPressedAction(repo, proj.projectId)) }
            }
            .show()

        // if they press discard
        return function
    }
}