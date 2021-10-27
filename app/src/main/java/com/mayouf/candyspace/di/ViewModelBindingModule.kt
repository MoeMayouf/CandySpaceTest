package com.mayouf.candyspace.di

import androidx.lifecycle.ViewModelProvider
import com.mayouf.candyspace.factory.InjectingViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBindingModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InjectingViewModelFactory): ViewModelProvider.Factory
}