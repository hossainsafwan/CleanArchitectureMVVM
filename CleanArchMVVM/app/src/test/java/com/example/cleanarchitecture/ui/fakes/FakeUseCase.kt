package com.example.cleanarchitecture.ui.fakes

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.data.models.Country
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUseCase : GetCountryUseCase {
    private lateinit var output : Resource<List<Country>>
    override suspend fun invoke(): Flow<Resource<List<Country>>> = flow {
            emit(output)
    }

    fun setOutput(output: Resource<List<Country>>) {
        this.output = output
    }
}