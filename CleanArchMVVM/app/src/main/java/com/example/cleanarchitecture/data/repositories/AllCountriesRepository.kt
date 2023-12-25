package com.example.cleanarchitecture.data.repositories

import com.example.cleanarchitecture.data.models.CountryDTO
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

interface AllCountriesRepository {
    suspend fun getAllCountries() : Flow<Resource<List<CountryDTO>>>
}