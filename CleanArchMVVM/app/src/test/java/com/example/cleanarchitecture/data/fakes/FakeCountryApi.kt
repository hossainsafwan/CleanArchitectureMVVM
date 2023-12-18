package com.example.cleanarchitecture.data.fakes

import android.icu.util.Output
import com.example.cleanarchitecture.data.models.CountryDTO
import com.example.cleanarchitecture.data.network.CountryAPI
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.BufferedSource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class FakeCountryApi : CountryAPI {
    private lateinit var output: List<CountryDTO>
    private lateinit var errorType: ErrorType

    override suspend fun getCountries(): List<CountryDTO> {
        when (errorType) {
            ErrorType.NONE -> {
                return output
            }

            ErrorType.HTTP_ERROR -> {
                throw HttpException(
                    Response.error<ResponseBody>
                        (500, "".toResponseBody("".toMediaTypeOrNull()))
                )
            }

            ErrorType.IO_EXCEPTION -> {
                throw IOException()
            }

            else -> {
                throw Exception()
            }
        }
    }

    fun setOutput(output: List<CountryDTO>, errorType: ErrorType = ErrorType.NONE) {
        this.output = output
        this.errorType = errorType
    }
}

enum class ErrorType {
    NONE, HTTP_ERROR, IO_EXCEPTION, GENERAL_EXCEPTION
}