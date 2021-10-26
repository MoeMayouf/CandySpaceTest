package com.mayouf.data.respository

import com.mayouf.data.datastore.StackExchangeRemoteDataStore
import com.mayouf.data.mapper.DataStackExchangeToDomainStackExchangeMapper
import com.mayouf.domain.entities.DomainStackExchange
import com.mayouf.domain.repository.StackExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope

class StackExchangeRepositoryImpl constructor(
    private val remoteDataStore: StackExchangeRemoteDataStore,
    private val stackExchangeMapper: DataStackExchangeToDomainStackExchangeMapper
) : StackExchangeRepository {
    override suspend fun getStackExchangeUsers(
        order: String,
        sort: String,
        name: String
    ): Flow<DomainStackExchange> = supervisorScope {
        remoteDataStore.getStackExchangeUsers(order, sort, name)
            .map { stackExchangeMapper.toDomainModel(it) }
    }
}