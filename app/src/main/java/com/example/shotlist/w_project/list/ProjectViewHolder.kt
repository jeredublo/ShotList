package com.example.shotlist.w_project.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shotlist.R
import com.example.shotlist.w_project.data_structs.Project

class ProjectViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.project_card, parent, false)) {

    fun bind(project: Project, onClick: ((Project) -> Unit)?) {
        onClick?.let { itemView.setOnClickListener { it(project) } }

        // project name
        itemView.findViewById<TextView>(R.id.project_name)?.text = project.name
// <link href='https://css.gg/ {ICONNAME} .css' rel='stylesheet'>
        // icon - same for all for now. eventually want to change image based on project type
//        itemView.findViewById<>()

    }

}