package com.example.cleanarchitecture.domain.usecases

import com.example.cleanarchitecture.data.models.Country
import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCountryUseCaseImpl(
    private val countryRepository: AllCountriesRepository
) : GetCountryUseCase {
    override suspend fun invoke(): Flow<Resource<List<Country>>> = countryRepository.getAllCountries()
}