package com.example.shotlist.base_mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shotlist.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}