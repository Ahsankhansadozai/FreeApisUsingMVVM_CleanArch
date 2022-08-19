package com.example.multiviewsrecycler.domain.usecases

import com.example.multiviewsrecycler.common.Resource
import com.example.multiviewsrecycler.data.model.toEntryDto
import com.example.multiviewsrecycler.domain.dto.EntryDto
import com.example.multiviewsrecycler.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetApisUseCase @Inject constructor(
    private val repository: ApiRepository,
) {
    operator fun invoke(): Flow<Resource<List<EntryDto>>> = flow {
        emit(Resource.Loading<List<EntryDto>>())
        try {
            val apis = repository.hGetApisDataFromNetwork().map { it.toEntryDto() }

            emit(Resource.Success<List<EntryDto>>(apis))

        } catch (e: HttpException) {
            emit(
                Resource.Error<List<EntryDto>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )

        } catch (e: IOException) {
            emit(
                Resource.Error<List<EntryDto>>(
                    e.localizedMessage ?: "Couldn't reach server. Check internet Connection"
                )
            )
        }


    }


}