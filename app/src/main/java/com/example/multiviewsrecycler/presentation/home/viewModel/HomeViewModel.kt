package com.example.multiviewsrecycler.presentation.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multiviewsrecycler.common.DataState
import com.example.multiviewsrecycler.common.Resource
import com.example.multiviewsrecycler.domain.dto.EntryDto
import com.example.multiviewsrecycler.domain.usecases.GetApisUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.internal.notify
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hGetApisUseCase: GetApisUseCase,

    ) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<EntryDto>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<EntryDto>>> get() = _dataState

    init {
        getCoins()
    }

    private fun getCoins() {
        hGetApisUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Timber.d("Success")
                    _dataState.value = DataState.Success(result.data ?: emptyList())
                }

                is Resource.Loading -> {
                    _dataState.value = DataState.Loading
                }
                is Resource.Error -> {
                    _dataState.value = DataState.Error(Exception(result.message
                        ?: "Unexpected error occurred"))
                }
            }

        }.launchIn(viewModelScope)
    }


}
