package com.example.cleanarchitecture.data.network.di

import com.example.cleanarchitecture.data.network.CountryAPI
import com.example.cleanarchitecture.data.network.CountryAPI.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CountryBaseUrl

@Module
class NetworkModule {

    @Provides
    @CountryBaseUrl
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    fun provideRetrofit(@CountryBaseUrl countryBaseUrl: String): Retrofit =
        Retrofit.Builder().baseUrl(countryBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create()).build()

    @Provides
    fun provideCountryAPI(retrofit: Retrofit): CountryAPI = retrofit.create(CountryAPI::class.java)
}