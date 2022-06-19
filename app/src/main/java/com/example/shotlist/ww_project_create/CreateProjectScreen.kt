package com.example.shotlist.ww_project_create

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.BaseScreen
import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.repository.SLDatabase
import com.example.shotlist.repository.SLRepository
import com.example.shotlist.w_project.actions.AddButtonClickedAction
import com.example.shotlist.w_project.actions.FilterClickedAction
import com.example.shotlist.w_project.data_structs.ProjectListState
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

    BaseScreen(navigator = navigator, viewModel = viewModel, ) {
        CreateProjectScreenContent(it, repo, viewModel.performAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
// render screen based on state
fun CreateProjectScreenContent(
    state: CreateProjectState,
    repo: SLRepository,
    performAction: (BaseAction<CreateProjectState>) -> Unit)
{
    Column() {      // this is the page
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {     // this is the top
            Row(
                modifier = Modifier.clickable { performAction(FilterClickedAction()) },
                horizontalArrangement = Arrangement.Start) {    // row with filter
                Text(state.sortBy.title)
                Image(painterResource(state.sortBy.icon), "icon for filter")
            }
            Text("Projects found 0")
        }
        when (state.projectList) {
            is DataResult.Loading -> {
                Text("Loading")
                Column(modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally) {
                    // the stuff that goes in the column
                    CircularProgressIndicator()
                }
            }
            is DataResult.Success -> {
                Text("Success")
//                LazyColumn(modifier = Modifier
//                    .fillMaxSize()
//                    .padding(10.dp)) {  // lazycolumn is recycler view
//                    items(state.projectList.data) { project ->
//                        Card(modifier = Modifier
//                            .fillMaxWidth()
//                            .combinedClickable(
//                                onClick = { performAction(ProjectLongPressedAction(repo, project.projectId)) })
//                        )
//                        {
//                            Row {
//                                // icon
//                                Image(painterResource(R.drawable.ic_baseline_playlist_play_24), "shotlist icon")
//                                // project name, and then production date
//                                Column {
//                                    Text(project.name)
//                                    Text(project.date)
//                                }
//                            }
//                        }
//                    }
//                }
                Button(
                    onClick = { performAction(AddButtonClickedAction()) },
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    ),
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_add_24),
                        contentDescription = "Plus sign",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Add project")
                }

            }

            is DataResult.Error -> {
                Text("Error")
                Log.e("error render", state.projectList.errorMessage)
                Text("Error loading your projects")
            }
        }
    }

}
