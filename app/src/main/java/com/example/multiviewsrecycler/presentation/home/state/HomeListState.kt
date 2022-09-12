/*
* Sardar Ahsan Khan
* 3/8/22
*
* */

package com.example.multiviewsrecycler.presentation.home.state

import com.example.multiviewsrecycler.domain.dto.EntryDto

data class HomeListState(
    val isLoading: Boolean = false,
    val apis: List<EntryDto> = emptyList(),
    val error: String = "",
)
