package com.example.cleanarchitecture.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.domain.models.CountryDomainModel
import com.example.cleanarchitecture.ui.models.CountryListUIState
import com.example.cleanarchitecture.ui.fakes.FakeUseCase
import com.example.cleanarchitecture.ui.models.CountryUIModel
import com.example.cleanarchitecture.ui.viewmodels.CountryViewModel
import com.example.cleanarchitecture.util.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CountryDomainModelViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()


    private var fakeUseCase = FakeUseCase()
    private lateinit var underTest: CountryViewModel
    private val output = mutableListOf(
        CountryDomainModel("Bangladesh", "BD", "fake_url_bd"),
        CountryDomainModel("Canada", "CA", "fake_url_ca"),
        CountryDomainModel("United States", "US", "fake_url_us")
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        underTest = CountryViewModel(fakeUseCase)
    }

    @Test
    fun `When successful proper output is emitted`() {
        fakeUseCase.setOutput(Resource.Success(output))

        underTest.getCountries()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            (underTest.countryListState.value as CountryListUIState.Success).data, listOf(
                CountryUIModel("Bangladesh", "BD", "fake_url_bd"),
                CountryUIModel("Canada", "CA", "fake_url_ca"),
                CountryUIModel("United States", "US", "fake_url_us")
            )

        )
    }

    @Test
    fun `When loading proper output is emitted`() {
        fakeUseCase.setOutput(Resource.Loading())

        underTest.getCountries()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            underTest.countryListState.value::class.java,
            CountryListUIState.Loading::class.java
        )
    }

    @Test
    fun `When http error proper output is emitted`() {
        fakeUseCase.setOutput(Resource.Error(message = R.string.server_error))

        underTest.getCountries()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            (underTest.countryListState.value as CountryListUIState.Failure).message,
            R.string.server_error
        )
    }

    @Test
    fun `When network error proper output is emitted`() {
        fakeUseCase.setOutput(Resource.Error(message = R.string.network_error))

        underTest.getCountries()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            (underTest.countryListState.value as CountryListUIState.Failure).message,
            R.string.network_error
        )
    }

    @Test
    fun `When general error proper output is emitted`() {
        fakeUseCase.setOutput(Resource.Error(message = R.string.general_error))

        underTest.getCountries()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            (underTest.countryListState.value as CountryListUIState.Failure).message,
            R.string.general_error
        )
    }

    @Test
    fun `Get proper search value when a value is set for search query`() {
        underTest.fetchCountryListFromQuery("Canada")

        assertEquals(underTest.searchQuery, "Canada")
    }
}