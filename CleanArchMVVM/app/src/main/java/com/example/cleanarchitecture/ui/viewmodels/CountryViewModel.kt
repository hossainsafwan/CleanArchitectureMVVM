package com.example.cleanarchitecture.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.models.Country
import com.example.cleanarchitecture.domain.GetCountryUseCase
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryViewModel(private val useCase: GetCountryUseCase) : ViewModel() {

    private val _countryList = MutableLiveData<List<Country>>()
    val countryList: LiveData<List<Country>>?
        get() = _countryList

    fun getCountries() {

        viewModelScope.launch {
            useCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        _countryList.value = resource.data
                    }

                    is Resource.Error -> {
                    }
                }
            }
        }
    }
}