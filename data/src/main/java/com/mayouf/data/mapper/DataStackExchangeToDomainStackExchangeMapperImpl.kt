package com.mayouf.data.mapper

import com.mayouf.data.entities.DataItems
import com.mayouf.data.entities.DataStackExchange
import com.mayouf.domain.entities.DomainBadgeCounts
import com.mayouf.domain.entities.DomainItems
import com.mayouf.domain.entities.DomainStackExchange
import javax.inject.Inject

interface DataStackExchangeToDomainStackExchangeMapper {
    fun toDomainModel(dataStackExchange: DataStackExchange): DomainStackExchange
}

class DataStackExchangeToDomainStackExchangeMapperImpl @Inject constructor() :
    DataStackExchangeToDomainStackExchangeMapper {
    override fun toDomainModel(dataStackExchange: DataStackExchange): DomainStackExchange =
        DomainStackExchange(
            items = dataStackExchange.items.map { toDomainItems(it) },
            hasMore = dataStackExchange.hasMore,
            quotaMax = dataStackExchange.quotaMax,
            quotaRemaining = dataStackExchange.quotaRemaining
        )

    private fun toDomainItems(dataItems: DataItems): DomainItems = DomainItems(
        dataBadgeCounts = DomainBadgeCounts(
            bronze = dataItems.dataBadgeCounts.bronze,
            silver = dataItems.dataBadgeCounts.silver,
            gold = dataItems.dataBadgeCounts.gold
        ),
        reputation = dataItems.reputation,
        creationDate = dataItems.creationDate,
        userId = dataItems.userId,
        location = dataItems.location,
        profileImage = dataItems.profileImage,
        displayName = dataItems.displayName
    )

}