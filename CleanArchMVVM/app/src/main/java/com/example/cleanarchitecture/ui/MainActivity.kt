package com.example.cleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.data.network.RetrofitInstance
import com.example.cleanarchitecture.data.repositories.AllCountriesRepositoryImpl
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import com.example.cleanarchitecture.domain.GetCountryUseCaseImpl
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel
import com.example.cleanarchitecture.ui.viewmodels.factories.CountryViewModelFactory
import com.example.cleanarchitecture.ui.views.CountryListAdapter

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

        viewModel.countryModel?.observe(this, Observer {
            binding.countryList.adapter = CountryListAdapter().apply {
                submitList(it.countryList)
            }
            binding.progressBar.isVisible = it.isLoading
            it.errorMessage?.let {
                Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
            }
        })

        if (viewModel.countryModel?.value == null) {
            viewModel.getCountries()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}