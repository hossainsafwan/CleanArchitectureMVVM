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

    private val _countryModel = MutableStateFlow<CountryListUIState>(CountryListUIState.Initial)
    val countryListState: StateFlow<CountryListUIState>
        get() = _countryModel
    private var _searchQuery: String = ""
    val searchQuery get() = _searchQuery

    fun getCountries() {
        viewModelScope.launch {
            useCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _countryModel.value = CountryListUIState.Loading
                    }

                    is Resource.Success -> {
                        resource.data?.let { list ->
                            _countryModel.value = (CountryListUIState.Success(
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
                        _countryModel.value =
                            (CountryListUIState.Failure(message = resource.message!!))
                    }
                }
            }
        }
    }

    fun fetchCountryListFromQuery(query: String = searchQuery) : List<CountryUIModel> {
        _searchQuery = query
        return getCountryList()
    }

    private fun getCountryList() = if (countryListState.value is CountryListUIState.Loading ||
        countryListState.value is CountryListUIState.Failure ||
        countryListState.value is CountryListUIState.Initial
    ) {
        emptyList()
    } else if (searchQuery.isEmpty()) {
        ((countryListState.value) as CountryListUIState.Success).data
    } else {
        ((countryListState.value) as CountryListUIState.Success).data.filter {
            it.countryName.contains(searchQuery, true)
        }
    }
}