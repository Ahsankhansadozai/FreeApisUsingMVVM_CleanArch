package com.example.multiviewsrecycler.di

import com.example.multiviewsrecycler.BuildConfig
import com.example.multiviewsrecycler.common.Constants.BASE_URL
import com.example.multiviewsrecycler.data.remote.retrofit.ApisAPI
import com.example.multiviewsrecycler.data.repository.ApiRepositoryImpl
import com.example.multiviewsrecycler.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinApi(): ApisAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(ApisAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideCoinRepository(
        api: ApisAPI,
    ): ApiRepository {
        return ApiRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        val levelType: HttpLoggingInterceptor.Level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


}