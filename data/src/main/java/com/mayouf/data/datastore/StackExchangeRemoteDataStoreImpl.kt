package com.mayouf.data.datastore

import com.mayouf.data.api.StackExchangeApiService
import com.mayouf.data.entities.DataStackExchange
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StackExchangeRemoteDataStoreImpl @Inject constructor(private val apiService: StackExchangeApiService) :
    StackExchangeRemoteDataStore {
    override suspend fun getStackExchangeUsers(
        order: String,
        sort: String,
        name: String
    ): Flow<DataStackExchange> = apiService.getUsers(order, sort, name)

}