package com.example.cleanarchitecture

import android.app.Application
import com.example.cleanarchitecture.di.AppComponent
import com.example.cleanarchitecture.di.DaggerAppComponent

class CountryApplication : Application() {

    val applicationComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}