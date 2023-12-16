package com.example.cleanarchitecture.domain

import com.example.cleanarchitecture.data.models.Country
import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.withContext

class GetCountryUseCaseImpl(
    private val countryRepository: AllCountriesRepository
) : GetCountryUseCase {
    override suspend fun invoke(): Flow<Resource<List<Country>>> = countryRepository.getAllCountries()
}