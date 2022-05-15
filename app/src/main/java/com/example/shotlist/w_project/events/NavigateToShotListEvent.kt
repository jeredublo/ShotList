package com.example.shotlist.w_project.events

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.shotlist.base_mvi.BaseEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class NavigateToShotListEvent(
    private val projectId : String) : BaseEvent
{

    override fun performEvent(context: Context, navigator: DestinationsNavigator) {

    }

}