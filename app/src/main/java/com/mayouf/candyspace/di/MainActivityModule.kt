package com.mayouf.candyspace.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.mayouf.candyspace.MainActivity
import com.mayouf.candyspace.ui.StackExchangeFragment
import com.mayouf.presentation.viewmodel.StackExchangeUsersViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
internal abstract class MainActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @FragmentKey(StackExchangeFragment::class)
    abstract fun stackExchangeFragment(stackExchangeFragment: StackExchangeFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(StackExchangeUsersViewModel::class)
    @ExperimentalCoroutinesApi
    abstract fun stackViewModel(stackViewModel: StackExchangeUsersViewModel): ViewModel
}