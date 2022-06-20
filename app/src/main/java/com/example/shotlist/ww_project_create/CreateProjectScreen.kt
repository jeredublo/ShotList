package com.example.shotlist.ww_project_create

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseScreen
import com.example.shotlist.repository.SLDatabase
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.ww_project_create.actions.CPCinaFieldChangedAction
import com.example.shotlist.ww_project_create.actions.CPDateFieldChangedAction
import com.example.shotlist.ww_project_create.actions.CPDirFieldChangedAction
import com.example.shotlist.ww_project_create.actions.CPNameFieldChangedAction
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers

@Destination
@Composable
fun CreateProjectScreen(navigator: DestinationsNavigator) {
    val viewModel = createProjectViewModel()

    val dao = SLDatabase.getDatabase(LocalContext.current.applicationContext).slDao()
    val repo = SLRepository(dao, Dispatchers.IO)

    BaseScreen(navigator = navigator, viewModel = viewModel) {
        CreateProjectScreenContent(it, repo, viewModel.performAction)
    }
}

@Composable
// render screen based on state
fun CreateProjectScreenContent(
    state: CreateProjectState,
    repo: SLRepository,
    performAction: (BaseAction<CreateProjectState>) -> Unit)
{
    var projectName : String = ""
    var projectDirector : String = ""
    var projectCinematographer : String = ""
    var projectDate : String = ""

    Column {      // this is the page
        OutlinedTextField(
            value = projectName,
            onValueChange = { name : String -> performAction(CPNameFieldChangedAction(name)); projectName = name},
            label =  { Text(text = "Project Name") },
        )
        OutlinedTextField(
            value = projectDirector,
            onValueChange = { dir : String -> performAction(CPDirFieldChangedAction(dir))},
            label = { Text(text = "Director") }
        )
        OutlinedTextField(
            value = projectCinematographer,
            onValueChange = { cine : String -> performAction(CPCinaFieldChangedAction(cine))},
            label = { Text(text = "Cinematographer") }
        )
        OutlinedTextField(
            value = projectDate,
            onValueChange = { date : String -> performAction(CPDateFieldChangedAction(date))},
            label = { Text(text = "Production Date") }
        )

    }

}
