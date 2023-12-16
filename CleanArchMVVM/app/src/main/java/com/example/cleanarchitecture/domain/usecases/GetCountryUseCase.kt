package com.example.cleanarchitecture.domain.usecases

import com.example.cleanarchitecture.data.models.Country
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetCountryUseCase {
    suspend operator fun invoke() : Flow<Resource<List<Country>>>
}