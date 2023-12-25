package com.example.cleanarchitecture.ui.models

import androidx.annotation.StringRes
import com.example.cleanarchitecture.domain.models.Country

data class Result(
    val countryList: List<Country> = emptyList(),
    @StringRes
    val errorMessage: Int? = null,
    val isLoading: Boolean = false
)
