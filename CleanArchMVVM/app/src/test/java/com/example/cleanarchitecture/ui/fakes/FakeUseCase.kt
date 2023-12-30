package com.example.cleanarchitecture.ui.fakes

import com.example.cleanarchitecture.domain.models.CountryDomainModel
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUseCase : GetCountryUseCase {
    private lateinit var output : Resource<List<CountryDomainModel>>
    override suspend fun invoke(): Flow<Resource<List<CountryDomainModel>>> = flow {
            emit(output)
    }

    fun setOutput(output: Resource<List<CountryDomainModel>>) {
        this.output = output
    }
}