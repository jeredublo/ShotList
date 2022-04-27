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

    suspend fun getProjects(): Flow<DataResult<List<Project>?>> {
        return flow {
            try {
                emit(DataResult.Loading)
                // Grab projects from db
                if (dao.getProjects().isNotEmpty())
                    emit(DataResult.Success(dao.getProjects()))
                else {
                    // there are no projects
                    emit(DataResult.Success(null))
                }
            } catch (exception: Exception) {
                emit(DataResult.Error(exception.toString()))
            }
        }.flowOn(dispatcher)
    }

//    suspend fun getShots(): Flow<DataResult<List<Shot>>> {
//
//    }
}