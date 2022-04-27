package com.example.shotlist.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shotlist.w_project.data_structs.Project

@Dao
interface SLDao {
    @Query("SELECT * FROM project")
    fun getProjects(): List<Project>

    // Note: albumId needs to exist as a fieldname
    //@Query("SELECT * FROM track WHERE albumId IS :trackAlbumId")    // albumId : this needs to match the var name in the data class Track
    //fun getTracksForAlbum(trackAlbumId: String): List<Track>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProjects(projects: List<Project>)

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertAllTracks(tracks: List<Track>)
}
