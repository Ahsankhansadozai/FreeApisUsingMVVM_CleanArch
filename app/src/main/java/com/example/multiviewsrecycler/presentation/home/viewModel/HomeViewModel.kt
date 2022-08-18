package com.example.multiviewsrecycler.presentation.home.viewModel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val hList = mutableListOf("2", "3", "2", "3", "2", "3", "2", "3", "2", "3", "2", "3")

    fun hGetList(): MutableList<String> {
        return hList
    }


}