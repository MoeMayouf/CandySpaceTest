package com.mayouf.domain.repository

import com.mayouf.data.entities.DataStackExchange
import com.mayouf.domain.entities.DomainStackExchange
import kotlinx.coroutines.flow.Flow

interface StackExchangeRepository {
    suspend fun getStackExchangeUsers(order: String, sort: String, name: String): Flow<DomainStackExchange>
}