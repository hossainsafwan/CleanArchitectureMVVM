package com.example.cleanarchitecture.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel
import javax.inject.Inject

class CountryViewModelFactory @Inject constructor(private val useCase: GetCountryUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetCountryUseCase::class.java).newInstance(useCase)
    }
}