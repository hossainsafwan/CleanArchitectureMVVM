package com.example.cleanarchitecture.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.domain.GetCountryUseCase
import com.example.cleanarchitecture.domain.models.Result
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryViewModel(private val useCase: GetCountryUseCase) : ViewModel() {

    private val _countryModel = MutableLiveData<Result>()
    val countryModel: LiveData<Result>?
        get() = _countryModel

    fun getCountries() {
        viewModelScope.launch {
            useCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _countryModel.postValue(Result(isLoading = true))
                    }

                    is Resource.Success -> {
                        resource.data?.let {
                            _countryModel.postValue(Result(it))
                        }
                    }

                    is Resource.Error -> {
                        _countryModel.postValue(Result(errorMessage = resource.message))
                    }
                }
            }
        }
    }
}