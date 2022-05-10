package com.example.shotlist.ww_project_create

import android.view.View
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIFragment
import com.example.shotlist.repository.SLDatabase
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.ww_project_create.actions.DoneButtonClickedAction
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState
import com.example.shotlist.ww_project_create.updaters.CPCinematographerUpdater
import com.example.shotlist.ww_project_create.updaters.CPDateUpdater
import com.example.shotlist.ww_project_create.updaters.CPDirectorUpdater
import com.example.shotlist.ww_project_create.updaters.CPNameUpdater
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers

class CreateProjectFragment : MVIFragment<CreateProjectState, CreateProjectViewModel>(CreateProjectViewModel::class.java, R.layout.createproject_fragment) {
    override fun getInitAction(): BaseAction<CreateProjectState>? {
        return null // nothing to set up for this screen
    }

    override fun initUI(view: View) {
        // attach click listener for the Done button
        view.findViewById<Button>(R.id.done_button).setOnClickListener{
            takeUserInput(view)
            context?.let {
                val dao = SLDatabase.getDatabase(it).slDao()
                val repo = SLRepository(dao, Dispatchers.IO)
                viewModel.performAction(DoneButtonClickedAction(repo))
            }
        }
    }

    override fun renderUI(newState: CreateProjectState) {
        // no loading/success/fail for this
    }



    // Helper function to grab all the user text and send it to our state
    private fun takeUserInput(view: View) {
        view.run{
            findViewById<TextInputLayout>(R.id.project_name).editText?.getText().toString().let {
                viewModel.sendUpdate(CPNameUpdater(it))
            }
            findViewById<TextInputLayout>(R.id.director_name).editText?.getText().toString().let {
                viewModel.sendUpdate(CPDirectorUpdater(it))
            }
            findViewById<TextInputLayout>(R.id.cinematographer_name).editText?.getText().toString().let {
                viewModel.sendUpdate(CPCinematographerUpdater(it))
            }
            findViewById<TextInputLayout>(R.id.production_date).editText?.getText().toString().let {
                viewModel.sendUpdate(CPDateUpdater(it))
            }
        }
    }
}