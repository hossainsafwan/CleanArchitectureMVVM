package com.example.cleanarchitecture.data.models

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("image")
    val image: String,
)