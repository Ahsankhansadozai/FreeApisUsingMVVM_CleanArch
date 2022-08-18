package com.example.multiviewsrecycler.presentation.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.multiviewsrecycler.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        home view binding
        */
        hSetViewContent()


    }

    private fun hSetViewContent() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}