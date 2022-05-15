package com.example.shotlist

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun Navigation() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}