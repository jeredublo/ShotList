package com.example.shotlist.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shotlist.w_project.data_structs.Project
import com.example.shotlist.x_shot.data_structs.Shot

@Dao
interface SLDao {
    // === PROJECTS ===
    @Query("SELECT * FROM project")
    fun getAllProjects(): List<Project>

    @Query("SELECT * FROM project WHERE id IS :projectId")  // TODO: ask chris if * is correct here
    fun findProject(projectId : String): Project?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProject(newProject : Project)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProjects(projects : List<Project>)


    // === SHOTS ===
    @Query("SELECT * FROM shot WHERE projectId IS :projectId")
    fun getAllShots(projectId : String): List<Shot>

    @Query("SELECT * FROM shot where shotId is :shotId")
    fun findShot(shotId : String) : Shot?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShot(newShot : Shot)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllShots(shots: List<Shot>)

    // Note: albumId needs to exist as a fieldname
    //@Query("SELECT * FROM track WHERE albumId IS :trackAlbumId")    // albumId : this needs to match the var name in the data class Track
    //fun getTracksForAlbum(trackAlbumId: String): List<Track>

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertAllTracks(tracks: List<Track>)
}
