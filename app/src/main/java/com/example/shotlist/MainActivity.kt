package com.example.shotlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.shotlist.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    //setContentView(R.layout.activity_main)
    }
}


// TODO: make a template for activity main since we are getting scrwewed by nav graph