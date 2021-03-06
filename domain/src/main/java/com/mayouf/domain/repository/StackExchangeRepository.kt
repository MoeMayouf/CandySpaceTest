package com.mayouf.domain.repository

import com.mayouf.domain.entities.DomainStackExchange
import kotlinx.coroutines.flow.Flow

interface StackExchangeRepository {
    suspend fun getStackExchangeUsers(order: String, site: String, name: String): Flow<DomainStackExchange>
}