package com.example.cleanarchitecture.domain.usecases

import com.example.cleanarchitecture.domain.models.Country
import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf

class GetCountryUseCaseImpl(
    private val countryRepository: AllCountriesRepository
) : GetCountryUseCase {
    override suspend fun invoke(): Flow<Resource<List<Country>>> {
        lateinit var output: Flow<Resource<List<Country>>>
        countryRepository.getAllCountries().collectLatest { resource ->
            when (resource) {
                is Resource.Loading -> {
                    output = flowOf(Resource.Loading())
                }

                is Resource.Error -> {
                    output = flowOf(Resource.Error(message = resource.message))
                }

                is Resource.Success -> {
                    val mapper = resource.data?.map {
                        Country(it.name,it.code,it.image)
                    }
                    output = flowOf(Resource.Success(data = mapper))
                }
            }

        }
        return output
    }
}