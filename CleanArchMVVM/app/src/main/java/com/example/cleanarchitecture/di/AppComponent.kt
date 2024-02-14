package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.data.di.RepositoryModule
import com.example.cleanarchitecture.data.network.di.NetworkModule
import com.example.cleanarchitecture.domain.di.UseCaseModule
import com.example.cleanarchitecture.ui.MainActivity
import com.example.cleanarchitecture.ui.viewmodels.factories.di.ViewModelFactoryModule
import dagger.Component

@Component(
    modules =
    [NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}