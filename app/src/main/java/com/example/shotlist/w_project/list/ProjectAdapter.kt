package com.example.shotlist.w_project.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shotlist.w_project.data_structs.Project

class ProjectAdapter(
    var projectList: List<Project>,
    private val onClick: ((Project) -> Unit)? = null) : RecyclerView.Adapter<ProjectViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProjectViewHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) = holder.bind(projectList[position], onClick)

    override fun getItemCount() = projectList.size
}