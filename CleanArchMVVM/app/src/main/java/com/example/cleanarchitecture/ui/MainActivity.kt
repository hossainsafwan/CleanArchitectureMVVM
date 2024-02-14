package com.example.cleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitecture.CountryApplication
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import com.example.cleanarchitecture.ui.models.CountryListUIState
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel
import com.example.cleanarchitecture.ui.viewmodels.factories.CountryViewModelFactory
import com.example.cleanarchitecture.ui.views.CountryListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    @Inject lateinit var countryViewModelFactory: CountryViewModelFactory
    private val viewModel: CountryViewModel by viewModels {
        countryViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CountryApplication).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CountryListAdapter()
        binding.countryList.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countryListState.collectLatest { countryListUIState ->
                    when (countryListUIState) {
                        is CountryListUIState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is CountryListUIState.Success -> {
                            binding.progressBar.isVisible = false
                            showSearchedList(adapter)
                        }

                        is CountryListUIState.Failure -> {
                            binding.progressBar.isVisible = false
                            Snackbar.make(
                                binding.swipeToRefresh,
                                getString((viewModel.countryListState.value as CountryListUIState.Failure).message),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                        is CountryListUIState.Initial -> viewModel.getCountries()
                    }
                }
            }
        }

        binding.searchBar.addTextChangedListener { editable ->
            viewModel.searchQuery(editable.toString())
            showSearchedList(adapter)
        }

        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = false
            viewModel.getCountries()
            showSearchedList(adapter)
        }

    }

    private fun showSearchedList(adapter: CountryListAdapter) {
        val searchList =
            if (viewModel.countryListState.value is CountryListUIState.Loading ||
                viewModel.countryListState.value is CountryListUIState.Failure ||
                viewModel.countryListState.value is CountryListUIState.Initial
            ) {
                emptyList()
            } else if (viewModel.searchQuery.isEmpty()) {
                ((viewModel.countryListState.value) as CountryListUIState.Success).data
            } else {
                ((viewModel.countryListState.value) as CountryListUIState.Success).data.filter {
                    it.countryName.contains(viewModel.searchQuery, true)
                }
            }
        adapter.submitList(searchList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}