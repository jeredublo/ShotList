package com.example.shotlist.repository

import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.w_project.data_structs.Project
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SLRepository(
    private val dao : SLDao,
    private val dispatcher: CoroutineDispatcher)
{

    // ==PROJECTS==

    /**
     * getProjects. Emits loading, error and success while fetching
     * list of projects from the db.
     */
    suspend fun getAllProjects(): Flow<DataResult<List<Project>>> {
        return flow {
            try {
                emit(DataResult.Loading)
                // Grab projects from db
                val projectList = dao.getAllProjects()
                emit(DataResult.Success(projectList)) // NOTE: this might be empty if no projects exist in DB
            } catch (exception: Exception) {
                emit(DataResult.Error(exception.toString()))
            }
        }.flowOn(dispatcher)
    }

    /**
     * addProject(newProject). For adding a new and editing an existing project (?).
     */
    suspend fun addProject(newProject : Project) : Flow<DataResult<Project>> {
        return flow {
            try {
                emit(DataResult.Loading)
                dao.insertProject(newProject)          // add to db
//                val finProjList = dao.getAllProjects()
                emit(DataResult.Success(newProject)) // send back complete-r list
            } catch (exception: Exception) {
                emit(DataResult.Error(exception.toString()))
            }
        }.flowOn(dispatcher)
    }



    // ==SHOTS==
/*
    suspend fun getShots(): Flow<DataResult<List<Shot>?>> {

    }

*/
        /*
            // for me later: figure out what is passing in the albumID
        suspend fun getTracks(albumId : String) : Flow<DataResult<List<Track>>> {
            return flow {
                try {
                    emit(DataResult.Loading)
                    // DB
                    var trackResults = spotDao.getTracksForAlbum(albumId)
                    if(trackResults.isNotEmpty()) {
                        emit(DataResult.Success(trackResults))
                    }
                    else {
                        // Service
                        trackResults = spotifyService.getTracks(albumId).items
                        for(track in trackResults) {
                            track.albumId = albumId
                        }
                        spotDao.insertAllTracks(trackResults)   // loading in the db
                        emit(DataResult.Success(trackResults))
                    }
                }
                catch (exception: Exception) {
                    emit(DataResult.Error(exception.toString()))
                }
            }.flowOn(dispatcher)
        }
         */

}