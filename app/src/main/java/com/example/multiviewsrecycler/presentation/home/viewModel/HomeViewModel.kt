package com.example.multiviewsrecycler.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multiviewsrecycler.common.DataState
import com.example.multiviewsrecycler.common.Resource
import com.example.multiviewsrecycler.domain.dto.EntryDto
import com.example.multiviewsrecycler.domain.usecases.GetApisUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hGetApisUseCase: GetApisUseCase,

    ) : ViewModel() {
    private var hList = mutableListOf<String>()
    private val _dataState = MutableSharedFlow<DataState<List<EntryDto>>>()
    val dataState = _dataState.asSharedFlow()

    init {
        getCoins()
//        hLoopAll()
    }

    private fun getCoins() {
        hGetApisUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Timber.d("Success")
                    _dataState.emit(DataState.Success(result.data ?: emptyList()))
                }

                is Resource.Loading -> {
                    _dataState.emit(DataState.Loading)
                }
                is Resource.Error -> {
                    _dataState.emit(DataState.Error(Exception(result.message
                        ?: "Unexpected error occurred")))
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun hLoopAll() {
        viewModelScope.launch {
            IntRange(0, 9).map {
                async {
                    hHeavyTask()
                }

            }.forEach {
                Timber.d("hList : ${hList.size}")
                it.await().forEach { no ->
                    hList.add(no)
                }
            }
        }


    }

    private suspend fun hHeavyTask(): List<String> {
        Timber.d("call")
        delay(2000)
        return listOf("1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16")
    }


}
