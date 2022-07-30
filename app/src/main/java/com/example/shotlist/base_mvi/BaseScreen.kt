package com.example.shotlist.base_mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun <MyState> BaseScreen(                               // <- generics for function looks like this
    navigator: DestinationsNavigator,
    viewModel: MVIViewModel<MyState>,
    initialAction: BaseAction<MyState>? = null,
    content: @Composable ((MyState) -> Unit),
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {   /// setting to tru, itll run once in the beginning and thats it... if you set it to nav, itll change verytime nav changes
        initialAction?.let { viewModel.performAction(initialAction) }
        viewModel.eventFlow.collect { event ->
            event.performEvent(context, navigator)
        }
    }
    content(viewModel.state.value)
}