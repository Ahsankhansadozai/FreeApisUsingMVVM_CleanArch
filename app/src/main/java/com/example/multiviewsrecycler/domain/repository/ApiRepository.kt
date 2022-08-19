package com.example.multiviewsrecycler.domain.repository

import com.example.multiviewsrecycler.data.model.ApiData

interface ApiRepository {

    suspend fun hGetApisDataFromNetwork(): ApiData

}