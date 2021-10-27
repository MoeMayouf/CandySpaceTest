package com.mayouf.presentation.mapper

import com.mayouf.domain.entities.DomainItems
import com.mayouf.domain.entities.DomainStackExchange
import com.mayouf.presentation.model.UiBadgeCounts
import com.mayouf.presentation.model.UiItems
import com.mayouf.presentation.model.UiStackExchange
import javax.inject.Inject

interface DomainStackExchangeToUiStackExchangeMapper {
    fun toUiModel(domainStackExchange: DomainStackExchange): UiStackExchange
}

class DomainStackExchangeToUiStackExchangeMapperImpl @Inject constructor() :
    DomainStackExchangeToUiStackExchangeMapper {
    override fun toUiModel(domainStackExchange: DomainStackExchange): UiStackExchange =
        UiStackExchange(
            items = domainStackExchange.items.map { toUiItems(it) },
            hasMore = domainStackExchange.hasMore,
            quotaRemaining = domainStackExchange.quotaRemaining,
            quotaMax = domainStackExchange.quotaMax
        )

    private fun toUiItems(items: DomainItems): UiItems = UiItems(
        dataBadgeCounts = UiBadgeCounts(
            items.dataBadgeCounts.bronze,
            items.dataBadgeCounts.silver,
            items.dataBadgeCounts.gold
        ),
        reputation = items.reputation,
        creationDate = items.creationDate,
        userId = items.userId,
        location = items.location,
        profileImage = items.profileImage,
        displayName = items.displayName
    )
}