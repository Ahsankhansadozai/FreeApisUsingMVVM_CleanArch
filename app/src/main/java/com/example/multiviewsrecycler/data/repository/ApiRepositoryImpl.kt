package com.example.multiviewsrecycler.data.repository

import com.example.multiviewsrecycler.data.model.Entry
import com.example.multiviewsrecycler.data.remote.retrofit.ApisAPI
import com.example.multiviewsrecycler.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: ApisAPI,
) : ApiRepository {
    override suspend fun hGetApisDataFromNetwork(): MutableList<Entry> {
        return api.hGetDataFromNetWork()
    }

}