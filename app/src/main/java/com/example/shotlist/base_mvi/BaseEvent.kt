package com.example.shotlist.base_mvi

import androidx.fragment.app.Fragment

interface BaseEvent {
    fun performEvent(fragment: Fragment)
}