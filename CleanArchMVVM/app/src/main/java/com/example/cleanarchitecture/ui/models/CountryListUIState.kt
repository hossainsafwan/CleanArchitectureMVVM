package com.example.cleanarchitecture.ui.models

import androidx.annotation.StringRes

sealed class CountryListUIState {
    data object Loading : CountryListUIState()
    class Success(val data : List<CountryUIModel>) : CountryListUIState()
    class Failure(@StringRes val message : Int) : CountryListUIState()
    data object Initial : CountryListUIState()
}



