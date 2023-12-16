package com.example.cleanarchitecture.util

import androidx.annotation.StringRes

sealed class Resource<T>(val data: T? = null, @StringRes val message: Int? = null) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>( @StringRes message: Int? = null) : Resource<T>(message = message)

}