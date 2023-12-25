package com.example.cleanarchitecture.domain.fakes

import com.example.cleanarchitecture.data.models.CountryDTO
import com.example.cleanarchitecture.domain.models.Country
import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAllCountriesRepository : AllCountriesRepository {
    private lateinit var output: Resource<List<CountryDTO>>
    override suspend fun getAllCountries(): Flow<Resource<List<CountryDTO>>> = flow {
       emit(output)
    }

    fun setOutput(output: Resource<List<CountryDTO>>) {
        this.output = output
    }
}