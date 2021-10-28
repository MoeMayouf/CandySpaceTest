package com.mayouf.domain.usecase

import com.mayouf.domain.entities.DomainStackExchange
import com.mayouf.domain.repository.StackExchangeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetStackExchangeUserUseCase {
    suspend fun execute(order: String, site: String, name: String): Flow<DomainStackExchange>
}

class GetStackExchangeUserUseCaseImpl @Inject constructor(
    private val repository: StackExchangeRepository
) : GetStackExchangeUserUseCase {
    override suspend fun execute(
        order: String,
        site: String,
        name: String
    ): Flow<DomainStackExchange> = repository.getStackExchangeUsers(order, site, name)
}