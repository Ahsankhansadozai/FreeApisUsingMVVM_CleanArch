package com.example.multiviewsrecycler.presentation.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.multiviewsrecycler.common.DataState
import com.example.multiviewsrecycler.domain.dto.EntryDto
import com.example.multiviewsrecycler.domain.usecases.GetApisUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hGetApisUseCase: GetApisUseCase,

    ) : ViewModel() {
    val mutableLiveDataApis = MutableLiveData<DataState<List<EntryDto>>>()


}

sealed class HomeStateEvent {

    object GetApisEvent : HomeStateEvent()

    object None : HomeStateEvent()
}