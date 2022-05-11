package com.example.shotlist.w_project.events

import androidx.fragment.app.Fragment
import com.example.shotlist.base_mvi.BaseEvent

class NavigateToShotListEvent(
    private val projectId : String) : BaseEvent
{

    override fun performEvent(fragment: Fragment) {

    }

}