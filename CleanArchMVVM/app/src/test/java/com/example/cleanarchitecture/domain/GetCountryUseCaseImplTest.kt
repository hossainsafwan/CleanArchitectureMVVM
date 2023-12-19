package com.example.cleanarchitecture.domain

import com.example.cleanarchitecture.data.models.Country
import com.example.cleanarchitecture.domain.fakes.FakeAllCountriesRepository
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCase
import com.example.cleanarchitecture.domain.usecases.GetCountryUseCaseImpl
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.util.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCountryUseCaseImplTest {

    private val fakeRepository = FakeAllCountriesRepository()
    private lateinit var underTest: GetCountryUseCase

    @Before
    fun setUp() {
        underTest = GetCountryUseCaseImpl(fakeRepository)
    }

    @Test
    fun `When success return from repository use case emits proper data`() {
        fakeRepository.setOutput(Resource.Success(listOf(Country("name", "code", "image-url"))))
        lateinit var mutableList: Resource<List<Country>>
        runTest {
            underTest.invoke().collectLatest {
                mutableList = it
            }

            assertEquals(mutableList::class.java, Resource.Success::class.java)
            assertEquals(mutableList?.data?.get(0)?.name, "name")
        }
    }

    @Test
    fun `When server error return from repository use case emits proper data`() {
        fakeRepository.setOutput(Resource.Error(message = R.string.server_error))
        lateinit var mutableList: Resource<List<Country>>
        runTest {
            underTest.invoke().collectLatest {
                mutableList = it
            }

            assertEquals(mutableList::class.java, Resource.Error::class.java)
            assertEquals(mutableList?.message, R.string.server_error)
        }
    }

    @Test
    fun `When network error return from repository use case emits proper data`() {
        fakeRepository.setOutput(Resource.Error(message = R.string.network_error))
        lateinit var mutableList: Resource<List<Country>>
        runTest {
            underTest.invoke().collectLatest {
                mutableList = it
            }

            assertEquals(mutableList::class.java, Resource.Error::class.java)
            assertEquals(mutableList?.message, R.string.network_error)
        }
    }

    @Test
    fun `When general error return from repository use case emits proper data`() {
        fakeRepository.setOutput(Resource.Error(message = R.string.general_error))
        lateinit var mutableList: Resource<List<Country>>
        runTest {
            underTest.invoke().collectLatest {
                mutableList = it
            }

            assertEquals(mutableList::class.java, Resource.Error::class.java)
            assertEquals(mutableList?.message, R.string.general_error)
        }
    }

    @Test
    fun `When loading return from repository use case emits proper data`() {
        fakeRepository.setOutput(Resource.Loading())
        lateinit var mutableList: Resource<List<Country>>
        runTest {
            underTest.invoke().collectLatest {
                mutableList = it
            }

            assertEquals(mutableList::class.java, Resource.Loading::class.java)
        }
    }
}