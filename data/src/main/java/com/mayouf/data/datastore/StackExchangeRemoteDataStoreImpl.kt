package com.mayouf.data.datastore

import com.mayouf.data.api.StackExchangeApiService
import com.mayouf.data.entities.data.DataStackExchange
import com.mayouf.data.mapper.ResponseStackExchangeToDataStackExchangeMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class StackExchangeRemoteDataStoreImpl @Inject constructor(
    private val apiService: StackExchangeApiService,
    private val responseMapper: ResponseStackExchangeToDataStackExchangeMapper
) :
    StackExchangeRemoteDataStore {
    private val _stackExchangeUsersSharedFlow = MutableStateFlow(getInitialDataStackExchange())

    private val stackExchangeUsersSharedFlow = _stackExchangeUsersSharedFlow.asSharedFlow()
    override suspend fun getStackExchangeUsers(
        order: String,
        site: String,
        name: String
    ): Flow<DataStackExchange> {
        try {
            responseMapper.toDataModel(apiService.getUsers(order, site, name))
                .let { companyInfoRepositoryModel ->
                    _stackExchangeUsersSharedFlow.emit(companyInfoRepositoryModel)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return stackExchangeUsersSharedFlow.distinctUntilChanged()
    }

    private fun getInitialDataStackExchange(): DataStackExchange = DataStackExchange(
        mutableListOf(), false, 1, 1
    )

}