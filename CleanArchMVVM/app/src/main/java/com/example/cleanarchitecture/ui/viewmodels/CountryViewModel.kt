package com.example.cleanarchitecture.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.ui.models.CountryListUIState
import com.example.cleanarchitecture.ui.models.CountryUIModel
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryViewModel(private val useCase: GetCountryUseCase) : ViewModel() {

    private val _countryListState = MutableStateFlow<CountryListUIState>(CountryListUIState.Initial)
    val countryListState: StateFlow<CountryListUIState>
        get() = _countryListState

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String>
        get() = _searchQuery

    fun getCountries() {
        viewModelScope.launch {
            useCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _countryListState.value = CountryListUIState.Loading()
                    }

                    is Resource.Success -> {
                        resource.data?.let { list ->
                            _countryListState.value = (CountryListUIState.Success(
                                data = list.filter {
                                    it.countryName.contains(searchQuery.value, true)
                                }.map { country ->
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
                        _countryListState.value =
                            (CountryListUIState.Failure(message = resource.message!!))
                    }
                }
            }
        }
    }

    fun fetchCountryListFromQuery(query: String = searchQuery.value) {
        _searchQuery.value = query
        getCountries()
    }
}