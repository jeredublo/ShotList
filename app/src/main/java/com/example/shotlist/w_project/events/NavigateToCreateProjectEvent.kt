package com.example.shotlist.w_project.events

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shotlist.base_mvi.BaseEvent
import com.example.shotlist.w_project.ProjectListFragment
import com.example.shotlist.w_project.ProjectListFragmentDirections

class NavigateToCreateProjectEvent() : BaseEvent {

    override fun performEvent(fragment: Fragment) {
        val action = ProjectListFragmentDirections.actionProjectListFragmentToNewProjectFragment()
        fragment.findNavController().navigate(action)

    }

}