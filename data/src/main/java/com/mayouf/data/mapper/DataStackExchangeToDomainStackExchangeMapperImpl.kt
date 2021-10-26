package com.mayouf.data.mapper

import com.mayouf.data.entities.DataStackExchange
import com.mayouf.domain.entities.DomainStackExchange

interface DataStackExchangeToDomainStackExchangeMapper {
    fun toDomainModel(dataStackExchange: DataStackExchange): DomainStackExchange
}

class DataStackExchangeToDomainStackExchangeMapperImpl :
    DataStackExchangeToDomainStackExchangeMapper {
    override fun toDomainModel(dataStackExchange: DataStackExchange): DomainStackExchange =
        DomainStackExchange(
            items = dataStackExchange.items,
            hasMore = dataStackExchange.hasMore,
            quotaMax = dataStackExchange.quotaMax,
            quotaRemaining = dataStackExchange.quotaRemaining
        )

}