package com.mayouf.domain.di

import com.mayouf.domain.repository.StackExchangeRepository
import com.mayouf.domain.usecase.GetStackExchangeUserUseCase
import com.mayouf.domain.usecase.GetStackExchangeUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class DomainModule {
    @Provides
    @Reusable
    fun provideGetStackExchangeUserUseCase(
        repository: StackExchangeRepository
    ): GetStackExchangeUserUseCase = GetStackExchangeUserUseCaseImpl(repository)


}