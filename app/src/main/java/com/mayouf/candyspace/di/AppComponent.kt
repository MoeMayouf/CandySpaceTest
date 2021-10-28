package com.mayouf.candyspace.di

import com.mayouf.candyspace.StackExchangeApp
import com.mayouf.data.di.ApiModule
import com.mayouf.domain.di.DomainModule
import com.mayouf.presentation.di.PresentationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DomainModule::class,
        PresentationModule::class,
        AppModule::class,
        MainActivityModule::class,
        FragmentBindingModule::class,
        ApiModule::class,
        ViewModelBindingModule::class
    ]
)

@Singleton
interface AppComponent : AndroidInjector<StackExchangeApp>