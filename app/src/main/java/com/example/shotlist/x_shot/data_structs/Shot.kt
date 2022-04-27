package com.example.shotlist.x_shot.data_structs

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Shot(
    val projectId : String,

    @PrimaryKey val shotId : String,


)
