package com.example.shotlist.w_project.data_structs

import androidx.room.PrimaryKey
import java.util.Date

data class Project (
    @PrimaryKey val id : String,
    val name : String,
    val director : String,
    val cinematographer : String,
    val date : Date,
)
