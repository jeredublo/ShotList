package com.example.shotlist.w_project

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseScreen
import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.repository.SLDatabase
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.actions.InitProjectsAction
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shotlist.R


@Destination
@Composable
fun ProjectListScreen(navigator: DestinationsNavigator) {
    val viewModel = projectListViewModel()

    val dao = SLDatabase.getDatabase(LocalContext.current.applicationContext).slDao()
    val repo = SLRepository(dao, Dispatchers.IO)

    BaseScreen(navigator = navigator, viewModel = viewModel, InitProjectsAction(repo)) {
        ProjectListScreenContent(it, viewModel.performAction)
    }
}

@Composable
// render screen based on state
fun ProjectListScreenContent(state: ProjectListState, performAction: (BaseAction<ProjectListState>) -> Unit) {
    Column() {      // this is the page
        Row(horizontalArrangement = Arrangement.SpaceBetween) {     // this is the top
            Row(horizontalArrangement = Arrangement.Start) {    // row with filter
                Text(state.sortBy.title)
                Image(painterResource(state.sortBy.icon),"icon for filter")
            }
            Text("Projects found 0")
        }
        when (state.projectList) {
            is DataResult.Loading -> {
                Column(modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally) {
                    // the stuff that goes in the column
                    CircularProgressIndicator()
                }
            }
            is DataResult.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(10.dp)) {  // lazycolumn is recycler view
                    items(state.projectList.data) { project ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Row() {
                                // icon
                                Image(painterResource(R.drawable.ic_baseline_playlist_play_24), "shotlist icon")
                                // project name, and then production date
                                Column() {
                                    Text(project.name)
                                    Text(project.date)
                                }
                            }
                        }
                    }
                }

            }

            is DataResult.Error -> {
                Log.e("error render", state.projectList.errorMessage)
                Text("Error loading your projects")
            }
        }
    }

}
