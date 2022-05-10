package com.example.shotlist.w_project.data_structs

import com.example.shotlist.R
import com.example.shotlist.base_mvi.DataResult

data class ProjectListState(
    val projectList : DataResult<List<Project>> = DataResult.Loading,
    val sortBy : SortedBy = SortedBy.NameAsc,
)

enum class SortedBy (val title : String, val icon : Int) {
    NameAsc("Name", R.drawable.ic_baseline_playlist_play_24),
    NameDesc("Name", R.drawable.ic_baseline_playlist_play_24),
    DateAsc("Date", R.drawable.ic_baseline_playlist_play_24), // @jess change these icons later
    DateDesc("Date", R.drawable.ic_baseline_playlist_play_24),
}