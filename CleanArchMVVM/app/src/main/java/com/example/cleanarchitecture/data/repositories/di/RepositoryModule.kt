package com.example.cleanarchitecture.data.repositories.di

import com.example.cleanarchitecture.data.network.CountryAPI
import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.data.repositories.AllCountriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideAllCountryRepository(allCountriesRepositoryImpl: AllCountriesRepositoryImpl)
            : AllCountriesRepository
}