package com.example.shotlist.ww_project_create.events

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.ww_project_create.CreateProjectFragmentDirections

class NavigateToProjectListEvent() : BaseEvent {
    override fun performEvent(fragment: Fragment) {
        val action = CreateProjectFragmentDirections.actionNewProjectFragmentToProjectListFragment()
        fragment.findNavController().navigate(action)
    }
}