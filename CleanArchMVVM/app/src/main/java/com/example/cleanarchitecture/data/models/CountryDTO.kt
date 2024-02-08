package com.example.cleanarchitecture.data.models

import com.squareup.moshi.Json

data class CountryDTO(
    @field:Json(name="name")
    val countryName: String,
    @field:Json(name="code")
    val countryCode: String,
    @field:Json(name="image")
    val imageUrl: String,
)