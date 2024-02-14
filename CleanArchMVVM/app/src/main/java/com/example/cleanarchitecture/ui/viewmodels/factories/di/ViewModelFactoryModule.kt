package com.example.cleanarchitecture.ui.viewmodels.factories.di

import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.ui.viewmodels.factories.CountryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideCountryViewModelFactory(useCase: GetCountryUseCase): CountryViewModelFactory =
        CountryViewModelFactory(useCase)
}