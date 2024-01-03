package com.example.cleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.data.network.RetrofitInstance
import com.example.cleanarchitecture.data.repositories.AllCountriesRepositoryImpl
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCaseImpl
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel
import com.example.cleanarchitecture.ui.viewmodels.factories.CountryViewModelFactory
import com.example.cleanarchitecture.ui.views.CountryListAdapter
import com.google.android.material.snackbar.Snackbar

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
        val adapter = CountryListAdapter()
        binding.countryList.adapter = adapter
        viewModel.countryModel?.observe(this, Observer {
            showSearchedList(adapter,viewModel)
            binding.progressBar.isVisible = it.isLoading
            it.errorMessage?.let { errorMessage ->
                Snackbar.make(
                    binding.swipeToRefresh,
                    getString(errorMessage),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        if (viewModel.countryModel?.value == null) {
            viewModel.getCountries()
        }

        binding.searchBar.addTextChangedListener { editable ->
            viewModel.searchQuery(editable.toString())
            showSearchedList(adapter,viewModel)
        }

        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = false
            viewModel.getCountries()
            showSearchedList(adapter,viewModel)
        }

    }

    private fun showSearchedList(adapter: CountryListAdapter, viewModel: CountryViewModel) {
        val searchList = if (viewModel.searchQuery.isEmpty()) {
            viewModel.countryModel?.value?.countryList ?: emptyList()
        } else {
            viewModel.countryModel?.value?.countryList?.filter {
                it.countryName.contains(viewModel.searchQuery,true)
            }
        }
        adapter.submitList(searchList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}