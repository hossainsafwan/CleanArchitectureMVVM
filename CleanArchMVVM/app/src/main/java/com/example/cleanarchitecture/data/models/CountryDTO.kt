package com.example.cleanarchitecture.data.models

import com.example.cleanarchitecture.domain.models.Country
import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("image")
    val image: String,

) {
    fun toCountry() = Country(
        name = name,
        code = code,
        imageURL = image
    )
}