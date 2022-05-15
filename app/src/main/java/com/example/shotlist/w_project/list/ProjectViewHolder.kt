package com.example.shotlist.w_project.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shotlist.R
import com.example.shotlist.w_project.data_structs.Project

class ProjectViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.project_card, parent, false)) {

    fun bind(
        project: Project,
        onClickFunction: ((Project) -> Unit)?)
    {

        onClickFunction?.let { itemView.setOnClickListener { it(project) } }

        // project name
        itemView.findViewById<TextView>(R.id.project_name)?.text = project.name

        // icon - same for all for now. eventually want to change image based on project type
        itemView.findViewById<ImageView>(R.id.card_icon)?.setImageResource(R.drawable.ic_baseline_playlist_play_24)

        // production date string
        itemView.findViewById<TextView>(R.id.production_date)?.text = "Production Date: " + project.date

    }

}