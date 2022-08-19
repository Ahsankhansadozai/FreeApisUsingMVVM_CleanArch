package com.example.multiviewsrecycler.data.model

import com.example.multiviewsrecycler.domain.dto.EntryDto
import com.google.gson.annotations.SerializedName

data class ApiData(
    var count: Int = 0, // 1425
    var entries: List<Entry> = listOf(),
)