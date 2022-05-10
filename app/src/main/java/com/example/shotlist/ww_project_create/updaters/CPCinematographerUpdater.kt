package com.example.shotlist.ww_project_create.updaters

import com.example.shotlist.base_mvi.BaseUpdater
import com.example.shotlist.ww_project_create.data_structs.CreateProjectState

class CPCinematographerUpdater(private val newCinema : String) : BaseUpdater<CreateProjectState> {
    override fun performUpdate(prevState: CreateProjectState): CreateProjectState {
         return prevState.copy(cinematographer = newCinema)

    }
}