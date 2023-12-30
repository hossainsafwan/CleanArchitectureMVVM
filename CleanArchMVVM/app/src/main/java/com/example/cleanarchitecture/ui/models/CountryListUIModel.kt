package com.example.cleanarchitecture.ui.models

import androidx.annotation.StringRes

data class CountryListUIModel(
    val countryList: List<CountryUIModel> = emptyList(),
    @StringRes
    val errorMessage: Int? = null,
    val isLoading: Boolean = false
)
