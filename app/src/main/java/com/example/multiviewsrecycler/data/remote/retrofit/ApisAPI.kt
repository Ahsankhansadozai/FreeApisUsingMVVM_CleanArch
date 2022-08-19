package com.example.multiviewsrecycler.data.remote.retrofit

import com.example.multiviewsrecycler.data.model.Entry
import retrofit2.http.GET

interface ApisAPI {

    @GET("/entries")
    suspend fun hGetDataFromNetWork(): MutableList<Entry>
}