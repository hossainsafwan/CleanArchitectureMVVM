package com.example.cleanarchitecture.data.models

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("name")
    val countryName: String,
    @SerializedName("code")
    val countryCode: String,
    @SerializedName("image")
    val imageUrl: String,
)