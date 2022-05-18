package com.example.shotlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.myapplication.ui.theme.ShotlistTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShotlistTheme{
                Navigation()
            }
        }
    }
}

@Destination
@Composable
fun Greeting(navigator: DestinationsNavigator, name: String) {
    Text(text = "Hello $name!")
}
