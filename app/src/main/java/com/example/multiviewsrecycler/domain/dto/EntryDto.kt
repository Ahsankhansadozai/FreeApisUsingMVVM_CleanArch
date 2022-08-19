package com.example.multiviewsrecycler.domain.dto

import com.example.multiviewsrecycler.data.model.Entry
import com.google.gson.annotations.SerializedName

data class EntryDto(
    @SerializedName("API")
    var api: String = "", // AdoptAPet
    @SerializedName("Auth")
    var auth: String = "", // apiKey
    @SerializedName("Category")
    var category: String = "", // Animals
    @SerializedName("Cors")
    var cors: String = "", // yes
    @SerializedName("Description")
    var description: String = "", // Resource to help get pets adopted
    @SerializedName("HTTPS")
    var https: Boolean = false, // true
    @SerializedName("Link")
    var link: String = "", // https://www.adoptapet.com/public/apis/pet_list.html
)


fun EntryDto.toEntry(): Entry {
    return Entry(
        api = api,
        auth = auth,
        category = category,
        cors = cors,
        description = description,
        https = https,
        link = link
    )
}


