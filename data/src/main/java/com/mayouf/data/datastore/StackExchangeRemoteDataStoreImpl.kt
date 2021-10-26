package com.mayouf.data.datastore

import com.mayouf.data.api.StackExchangeApiService
import com.mayouf.data.entities.DataStackExchange
import kotlinx.coroutines.flow.Flow

class StackExchangeRemoteDataStoreImpl constructor(private val apiService: StackExchangeApiService) :
    StackExchangeRemoteDataStore {
    override suspend fun getStackExchangeUsers(
        order: String,
        sort: String,
        name: String
    ): Flow<DataStackExchange> = apiService.getUsers(order, sort, name)

}