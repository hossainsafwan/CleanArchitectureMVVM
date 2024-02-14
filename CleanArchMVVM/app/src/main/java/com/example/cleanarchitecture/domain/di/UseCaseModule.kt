package com.example.cleanarchitecture.domain.di

import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetCountryUseCase(repository: AllCountriesRepository): GetCountryUseCase =
        GetCountryUseCaseImpl(repository)
}