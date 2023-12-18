package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.fakes.ErrorType
import com.example.cleanarchitecture.data.fakes.FakeCountryApi
import com.example.cleanarchitecture.data.models.Country
import com.example.cleanarchitecture.data.models.CountryDTO
import com.example.cleanarchitecture.data.repositories.AllCountriesRepositoryImpl
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.data.repositories.AllCountriesRepository
import com.example.cleanarchitecture.util.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AllCountriesRepositoryImplTest {

    private var fakeCountryApi = FakeCountryApi()
    private lateinit var underTest: AllCountriesRepository
    private val apiOutput = listOf(CountryDTO("Bangladesh", "BD", "imageUrl"))
    private val repositoryOutput = listOf(Country("Bangladesh", "BD", "imageUrl"))

    @Before
    fun setUp() {
        underTest = AllCountriesRepositoryImpl(fakeCountryApi)
    }

    @Test
    fun `When getAllCountries successfully called return loading then success`() {
        val mutableList = mutableListOf<Resource<List<Country>>>()
        fakeCountryApi.setOutput(apiOutput)

        runTest {
            underTest.getAllCountries().collectLatest {
                mutableList.add(it)
            }
        }

        assertEquals(mutableList[0]::class.java, Resource.Loading::class.java)
        assertEquals(mutableList[1]::class.java, Resource.Success::class.java)
        assertEquals(mutableList[1].data, repositoryOutput)
    }

    @Test
    fun `When getAllCountries causes IO error return loading then proper error`() {
        val mutableList = mutableListOf<Resource<List<Country>>>()
        fakeCountryApi.setOutput(emptyList(), ErrorType.IO_EXCEPTION)

        runTest {
            underTest.getAllCountries().collectLatest {
                mutableList.add(it)
            }

            assertEquals(mutableList[0]::class.java, Resource.Loading::class.java)
            assertEquals(mutableList[1]::class.java, Resource.Error::class.java)
            assertEquals(mutableList[1].message, R.string.network_error)
        }
    }

    @Test
    fun `When getAllCountries causes server error return loading then error`() {
        val mutableList = mutableListOf<Resource<List<Country>>>()
        fakeCountryApi.setOutput(emptyList(), ErrorType.HTTP_ERROR)

        runTest {
            underTest.getAllCountries().collectLatest {
                mutableList.add(it)
            }
            assertEquals(mutableList[0]::class.java, Resource.Loading::class.java)
            assertEquals(mutableList[1]::class.java, Resource.Error::class.java)
            assertEquals(mutableList[1].message, R.string.server_error)
        }
    }

    @Test
    fun `When getAllCountries causes general error return loading then proper error`() {
        val mutableList = mutableListOf<Resource<List<Country>>>()
        fakeCountryApi.setOutput(emptyList(), ErrorType.GENERAL_EXCEPTION)

        runTest {
            underTest.getAllCountries().collectLatest {
                mutableList.add(it)
            }

            assertEquals(mutableList[0]::class.java, Resource.Loading::class.java)
            assertEquals(mutableList[1]::class.java, Resource.Error::class.java)
            assertEquals(mutableList[1].message, R.string.general_error)
        }
    }
}