package com.example.cleanarchitecture.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel

class CountryViewModelFactory(private val useCase: GetCountryUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) return CountryViewModel(
            useCase
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}