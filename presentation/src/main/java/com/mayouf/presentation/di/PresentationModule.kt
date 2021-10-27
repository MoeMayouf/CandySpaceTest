package com.mayouf.presentation.di

import com.mayouf.presentation.mapper.DomainStackExchangeToUiStackExchangeMapper
import com.mayouf.presentation.mapper.DomainStackExchangeToUiStackExchangeMapperImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class PresentationModule {
    @Provides
    @Reusable
    fun provideDomainStackExchangeToUiStackExchangeMapper(): DomainStackExchangeToUiStackExchangeMapper =
        DomainStackExchangeToUiStackExchangeMapperImpl()
}