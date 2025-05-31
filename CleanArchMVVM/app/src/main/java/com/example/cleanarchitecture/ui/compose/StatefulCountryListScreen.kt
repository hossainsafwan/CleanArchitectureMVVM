package com.example.cleanarchitecture.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cleanarchitecture.ui.models.CountryListUIState
import com.example.cleanarchitecture.ui.models.CountryUIModel
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel

@Composable
fun StatefulCountryListScreen(viewModel: CountryViewModel) {

    val countryListUIState by viewModel.countryListState.collectAsStateWithLifecycle()
    val searchValue by viewModel.searchQuery.collectAsStateWithLifecycle()
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var countryList by rememberSaveable { mutableStateOf(emptyList<CountryUIModel>()) }

    when (countryListUIState) {
        is CountryListUIState.Loading -> {
            isLoading = true
        }

        is CountryListUIState.Success -> {
            isLoading = false
            countryList = (countryListUIState as CountryListUIState.Success).data
        }

        is CountryListUIState.Failure -> {
            isLoading = false
        }

        is CountryListUIState.Initial -> {
            isLoading = true
            viewModel.getCountries()
        }
    }

    StatelessCountryListScreen(
        countryList, searchValue, isLoading,
        { query ->
            viewModel.fetchCountryListFromQuery(query)
        },
        onRefresh = { viewModel.getCountries() },
    )
}
