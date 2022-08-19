package com.example.multiviewsrecycler.data.repository

import com.example.multiviewsrecycler.data.model.ApiData
import com.example.multiviewsrecycler.data.remote.retrofit.ApisAPI
import com.example.multiviewsrecycler.domain.repository.ApiRepository
import timber.log.Timber
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: ApisAPI,
) : ApiRepository {

    override suspend fun hGetApisDataFromNetwork(): ApiData {
        Timber.d("Calling Api from repo")
        return api.hGetDataFromNetWork()
    }

}