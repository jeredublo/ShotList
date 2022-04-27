package com.example.shotlist.w_project.data_structs

import com.example.shotlist.base_mvi.DataResult

data class ProjectListState(
    val projectList : DataResult<List<Project>>,
    val sortBy : SortedBy,
)

enum class SortedBy {
    NameAsc, NameDesc, DateAsc, DateDesc,
}
