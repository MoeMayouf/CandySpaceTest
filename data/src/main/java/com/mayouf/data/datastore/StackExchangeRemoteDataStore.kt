package com.mayouf.data.datastore

import com.mayouf.data.entities.DataStackExchange
import kotlinx.coroutines.flow.Flow

interface StackExchangeRemoteDataStore {
    suspend fun getStackExchangeUsers(order: String, site: String, name: String): Flow<DataStackExchange>
}