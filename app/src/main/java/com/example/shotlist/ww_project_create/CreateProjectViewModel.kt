package com.example.shotlist.ww_project_create

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
import com.example.shotlist.ww_project_create.ViewModelFactory
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState


// NOTE: Nothing to add to this file! It's all implemented in the BaseViewModel
class CreateProjectViewModel : MVIViewModel<CreateProjectState>(CreateProjectState())

@Composable fun createProjectViewModel(
    // storeowner has the instance and manages it
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    savedStateRegistryOwner: SavedStateRegistryOwner = LocalSavedStateRegistryOwner.current
): CreateProjectViewModel {

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
            CreateProjectViewModel::class.java -> CreateProjectViewModel()

            else -> throw RuntimeException("Unknown view model $modelClass")
        } as T
    }
}