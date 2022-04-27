package com.example.shotlist.w_project

import com.example.shotlist.w_project.data_structs.Project

data class ProjectListState(
    val projectList : List<Project>,
    val sortBy : SortedBy,
)

enum class SortedBy {
    NameAsc, NameDesc, DateAsc, DateDesc,
}
