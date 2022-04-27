package com.example.shotlist.repository

import com.example.shotlist.base_mvi.DataResult
import com.example.shotlist.w_project.data_structs.Project
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

// want to run on the IO but we add it as a param so that we can change it out for testing later
// need the Dao in here as well when the db is added later
class SLRepository(
    private val dao : SLDao,
//    private val service: SLService,
    private val dispatcher: CoroutineDispatcher)
{

    suspend fun addProject(newProject : Project) {
//        if (dao.findProject(newProject.id) == null)
//            dao.insertProject(newProject);
//        else TODO: might not need this since itll just overwrite the existing mathcing project
        dao.insertProject(newProject);




    //load into the DB
            //no project exists, load new into DB
        // check if project exists
        // if project exists, then add new information and then insert back into db
        // add project to the
    }

    /**
     * getProjects. Emits loading, error and success while fetching
     * list of projects from the db.
     */
    suspend fun getProjects(): Flow<DataResult<List<Project>?>> {
        return flow {
            try {
                emit(DataResult.Loading)
                // Grab projects from db
                if (dao.getProjects().isNotEmpty())
                    emit(DataResult.Success(dao.getProjects())) // send projects we found in DB
                else {
                    emit(DataResult.Success(null))  // no projects found
                }
            } catch (exception: Exception) {
                emit(DataResult.Error(exception.toString()))
            }
        }.flowOn(dispatcher)
    }

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