package com.example.cleanarchitecture.ui.models

import androidx.annotation.StringRes

sealed class CountryListUIState {
    data class Loading(val data: List<CountryUIModel> = emptyList<CountryUIModel>()) :
        CountryListUIState()

    class Success(val data: List<CountryUIModel>) : CountryListUIState()
    data class Failure(
        val data: List<CountryUIModel> = emptyList<CountryUIModel>(),
        @StringRes val message: Int
    ) : CountryListUIState()

    data object Initial : CountryListUIState()
}



