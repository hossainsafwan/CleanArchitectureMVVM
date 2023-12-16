package com.example.cleanarchitecture.data.network

import com.example.cleanarchitecture.data.models.CountryDTO
import retrofit2.http.GET

interface CountryAPI {
    @GET("countries.json")
    suspend fun getCountries() : List<CountryDTO>
    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/hossainsafwan/JsonFiles/main/"
    }
}