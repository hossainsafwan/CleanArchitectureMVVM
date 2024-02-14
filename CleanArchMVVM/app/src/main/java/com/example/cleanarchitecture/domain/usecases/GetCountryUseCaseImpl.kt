package com.example.cleanarchitecture.domain.usecases

import com.example.cleanarchitecture.domain.models.CountryDomainModel
import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetCountryUseCaseImpl @Inject constructor(
    private val countryRepository: AllCountriesRepository
) : GetCountryUseCase {
    override suspend fun invoke(): Flow<Resource<List<CountryDomainModel>>> =
        countryRepository.getAllCountries().transform { resource ->
            when (resource) {
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }

                is Resource.Error -> {
                    emit(Resource.Error(message = resource.message))
                }

                is Resource.Success -> {
                    val mapper = resource.data?.map {
                        CountryDomainModel(it.countryName, it.countryCode, it.imageUrl)
                    }
                    emit(Resource.Success(data = mapper))
                }
            }

        }

}