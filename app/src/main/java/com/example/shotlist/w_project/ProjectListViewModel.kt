package com.example.shotlist.w_project

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.savedstate.SavedStateRegistryOwner
import com.example.shotlist.base_mvi.MVIViewModel
import com.example.shotlist.w_project.data_structs.ProjectListState
import com.example.shotlist.ww_project_create.ProjectListViewModel
import com.example.shotlist.ww_project_create.ViewModelFactory


// NOTE: Nothing to add to this file! It's all implemented in the BaseViewModel
class ProjectListViewModel : MVIViewModel<ProjectListState>(ProjectListState())

@Composable fun projectListViewModel(
    // storeowner has the instance and manages it
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    savedStateRegistryOwner: SavedStateRegistryOwner = LocalSavedStateRegistryOwner.current
): ProjectListViewModel {

    return androidx.lifecycle.viewmodel.compose.viewModel(
        viewModelStoreOwner = viewModelStoreOwner,
        factory = ViewModelFactory(
            owner = savedStateRegistryOwner,
            defaultArgs = (savedStateRegistryOwner as? NavBackStackEntry)?.arguments
        )
    )

}

class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?,
) : AbstractSavedStateViewModelFactory(
    owner,
    defaultArgs
) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return when (modelClass) {
            ProjectListViewModel::class.java -> ProjectListViewModel()

            else -> throw RuntimeException("Unknown view model $modelClass")
        } as T
    }
}