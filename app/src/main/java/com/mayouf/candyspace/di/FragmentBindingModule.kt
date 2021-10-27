package com.mayouf.candyspace.di

import androidx.fragment.app.FragmentFactory
import com.mayouf.candyspace.factory.InjectingFragmentFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FragmentBindingModule {
    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}