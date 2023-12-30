package com.example.cleanarchitecture.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.ui.models.CountryListUIModel
import com.example.cleanarchitecture.ui.models.CountryUIModel
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryViewModel(private val useCase: GetCountryUseCase) : ViewModel() {

    private val _countryModel = MutableLiveData<CountryListUIModel>()
    val countryModel: LiveData<CountryListUIModel>?
        get() = _countryModel

    fun getCountries() {
        viewModelScope.launch {
            useCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _countryModel.postValue(CountryListUIModel(isLoading = true))
                    }

                    is Resource.Success -> {
                        resource.data?.let { list->
                            _countryModel.postValue(CountryListUIModel(
                                countryList = list.map { country->
                                    CountryUIModel(
                                        country.countryName,
                                        country.countryCode,
                                        country.imageURL
                                    )
                                }
                            ))
                        }
                    }

                    is Resource.Error -> {
                        _countryModel.postValue(CountryListUIModel(errorMessage = resource.message))
                    }
                }
            }
        }
    }
}