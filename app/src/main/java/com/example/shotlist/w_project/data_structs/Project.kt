package com.example.shotlist.w_project.data_structs

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Project (
    @PrimaryKey val projectId : String,

    val name : String,
    val director : String,
    val cinematographer : String,
    val date : Date,
)
