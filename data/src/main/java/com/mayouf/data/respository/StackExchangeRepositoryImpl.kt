package com.mayouf.data.respository

import com.mayouf.data.datastore.StackExchangeRemoteDataStore
import com.mayouf.data.mapper.DataStackExchangeToDomainStackExchangeMapper
import com.mayouf.domain.entities.DomainStackExchange
import com.mayouf.domain.repository.StackExchangeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class StackExchangeRepositoryImpl @Inject constructor(
    private val remoteDataStore: StackExchangeRemoteDataStore,
    private val stackExchangeMapper: DataStackExchangeToDomainStackExchangeMapper
) : StackExchangeRepository {
    override suspend fun getStackExchangeUsers(
        order: String,
        site: String,
        name: String
    ): Flow<DomainStackExchange> = supervisorScope {
        remoteDataStore.getStackExchangeUsers(order, site, name)
            .map { stackExchangeMapper.toDomainModel(it) }
    }
}