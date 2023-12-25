package com.example.cleanarchitecture.data.repositories

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.data.models.CountryDTO
import com.example.cleanarchitecture.data.network.CountryAPI
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AllCountriesRepositoryImpl(private val countryAPI: CountryAPI) : AllCountriesRepository {
    private lateinit var countryList : List<CountryDTO>
    override suspend fun getAllCountries(): Flow<Resource<List<CountryDTO>>> = flow {
        emit(Resource.Loading())
        try {
            countryList = countryAPI.getCountries()
            emit(Resource.Success(countryList))
        } catch (e: HttpException) {
            emit(Resource.Error(message = R.string.server_error))
        } catch (e: IOException) {
            emit(Resource.Error(message = R.string.network_error))
        } catch (e: Exception) {
            emit(Resource.Error(message = R.string.general_error))
        }
    }
}