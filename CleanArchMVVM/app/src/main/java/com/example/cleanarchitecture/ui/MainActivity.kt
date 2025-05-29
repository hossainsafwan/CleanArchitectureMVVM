package com.example.cleanarchitecture.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.example.cleanarchitecture.CountryApplication
import com.example.cleanarchitecture.ui.compose.StatefulCountryListScreen
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel
import com.example.cleanarchitecture.ui.viewmodels.factories.CountryViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var countryViewModelFactory: CountryViewModelFactory
    private val viewModel: CountryViewModel by viewModels {
        countryViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CountryApplication).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                StatefulCountryListScreen(
                    viewModel = viewModel,
                )
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}