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

    private val _countryListStatel = MutableStateFlow<CountryListUIState>(CountryListUIState.Initial)
    val countryListState: StateFlow<CountryListUIState>
        get() = _countryListStatel

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String>
        get() = _searchQuery

    fun getCountries() {
        viewModelScope.launch {
            useCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _countryListStatel.value = CountryListUIState.Loading
                    }

                    is Resource.Success -> {
                        resource.data?.let { list ->
                            _countryListStatel.value = (CountryListUIState.Success(
                                data = list.map { country ->
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
                        _countryListStatel.value =
                            (CountryListUIState.Failure(message = resource.message!!))
                    }
                }
            }
        }
    }

    fun fetchCountryListFromQuery(query: String = searchQuery.value) {
        _searchQuery.value = query
        _countryListStatel.value = (CountryListUIState.Success(
            data = getQueriedCountryList()
        ))
    }

    private fun getQueriedCountryList() = if (countryListState.value is CountryListUIState.Loading ||
        countryListState.value is CountryListUIState.Failure ||
        countryListState.value is CountryListUIState.Initial
    ) {
        emptyList()
    } else if (searchQuery.value.isEmpty()) {
        ((countryListState.value) as CountryListUIState.Success).data
    } else {
        ((countryListState.value) as CountryListUIState.Success).data.filter {
            it.countryName.contains(searchQuery.value, true)
        }
    }
}