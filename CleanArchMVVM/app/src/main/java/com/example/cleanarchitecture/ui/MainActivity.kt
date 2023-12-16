package com.example.cleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.data.network.RetrofitInstance
import com.example.cleanarchitecture.data.repositories.AllCountriesRepositoryImpl
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import com.example.cleanarchitecture.domain.GetCountryUseCaseImpl
import com.example.cleanarchitecture.ui.viewmodels.factories.CountryViewModel
import com.example.cleanarchitecture.ui.viewmodels.factories.CountryViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = CountryViewModelFactory(
            GetCountryUseCaseImpl(
                AllCountriesRepositoryImpl(RetrofitInstance.countryAPI)
            )
        )
        val viewModel = ViewModelProvider(this, viewModelFactory)[CountryViewModel::class.java]

        viewModel.countryList?.observe(this, Observer {
        })

        if (viewModel.countryList?.value == null) {
            viewModel.getCountries()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}