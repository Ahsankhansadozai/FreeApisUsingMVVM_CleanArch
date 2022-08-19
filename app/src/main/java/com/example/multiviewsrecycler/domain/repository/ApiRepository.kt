package com.example.multiviewsrecycler.domain.repository

import com.example.multiviewsrecycler.data.model.Entry

interface ApiRepository {

    suspend fun hGetApisDataFromNetwork(): MutableList<Entry>

}