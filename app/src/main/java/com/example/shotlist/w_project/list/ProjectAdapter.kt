package com.example.shotlist.w_project.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.shotlist.w_project.data_structs.Project


class ProjectAdapter(
    private val onClick: ((Project) -> Unit)? = null,
    private val onLongClick: ((Project) -> Unit)? = null, )
    : ListAdapter<Project, ProjectViewHolder>(DiffCallback())
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProjectViewHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
        holder.bind(currentList[position], onLongClick) // TODO: ask chris if this works
    }

    override fun getItemCount() =
        currentList.size
}


private class DiffCallback : DiffUtil.ItemCallback<Project>() {

    //2
    override fun areItemsTheSame(oldItem: Project, newItem: Project) =
        oldItem.projectId == newItem.projectId

    //3
    override fun areContentsTheSame(oldItem: Project, newItem: Project) =
        oldItem == newItem
        // would normally check each item but since we are using a data class we can use this
}


/*
Diffutil:

Listadapter goes around the recycler view

    var projectList: List<Project>,   goes away since it manages it for me


 */


