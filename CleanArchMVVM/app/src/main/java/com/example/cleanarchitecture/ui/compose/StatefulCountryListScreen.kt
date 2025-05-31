package com.example.cleanarchitecture.ui.compose

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cleanarchitecture.ui.models.CountryListUIState
import com.example.cleanarchitecture.ui.models.CountryUIModel
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel

@Composable
fun StatefulCountryListScreen(viewModel: CountryViewModel) {

    val countryListUIState by viewModel.countryListState.collectAsStateWithLifecycle()
    val searchValue by viewModel.searchQuery.collectAsStateWithLifecycle()
    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    var countryList by rememberSaveable { mutableStateOf(emptyList<CountryUIModel>()) }

    when (countryListUIState) {
        is CountryListUIState.Loading -> {
            countryList = (countryListUIState as CountryListUIState.Loading).data
        }

        is CountryListUIState.Success -> {
            if (isRefreshing) {
                isRefreshing = false
            }
            countryList = (countryListUIState as CountryListUIState.Success).data
        }

        is CountryListUIState.Failure -> {
            if (isRefreshing) {
                isRefreshing = false
            }
            countryList = (countryListUIState as CountryListUIState.Failure).data
            Toast.makeText(
                LocalContext.current,
                (countryListUIState as CountryListUIState.Failure).message,
                Toast.LENGTH_LONG
            ).show()

        }

        is CountryListUIState.Initial -> {
            viewModel.getCountries()
        }
    }

    StatelessCountryListScreen(
        countryList = countryList,
        searchValue = searchValue,
        isLoading = countryListUIState is CountryListUIState.Loading,
        isRefreshing = isRefreshing,
        onValueChange = { query ->
            viewModel.fetchCountryListFromQuery(query)
        },
        onRefresh = {
            isRefreshing = true
            viewModel.getCountries()
        },
    )
}
