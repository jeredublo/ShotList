package com.example.shotlist.ww_project_create

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.platform.LocalContext
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseScreen
import com.example.shotlist.repository.SLDatabase
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.ww_project_create.actions.CreateCinaFieldChangedAction
import com.example.shotlist.ww_project_create.actions.CreateDateFieldChangedAction
import com.example.shotlist.ww_project_create.actions.CreateDirFieldChangedAction
import com.example.shotlist.ww_project_create.actions.CreateNameFieldChangedAction
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
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

@OptIn(ExperimentalMaterial3Api::class)
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
            onValueChange = { name : String -> performAction(CreateNameFieldChangedAction(name)); projectName = name},
            label =  { Text(text = "Project Name") },
        )
        OutlinedTextField(
            value = projectDirector,
            onValueChange = { dir : String -> performAction(CreateDirFieldChangedAction(dir))},
            label = { Text(text = "Director") }
        )
        OutlinedTextField(
            value = projectCinematographer,
            onValueChange = { cine : String -> performAction(CreateCinaFieldChangedAction(cine))},
            label = { Text(text = "Cinematographer") }
        )
        OutlinedTextField(
            value = projectDate,
            onValueChange = { date : String -> performAction(CreateDateFieldChangedAction(date))},
            label = { Text(text = "Production Date") }
        )

    }

}
