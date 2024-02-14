package com.example.cleanarchitecture.data.di

import com.example.cleanarchitecture.data.network.CountryAPI
import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.data.repositories.AllCountriesRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideAllCountryRepository(countryAPI: CountryAPI): AllCountriesRepository =
        AllCountriesRepositoryImpl(countryAPI)
}