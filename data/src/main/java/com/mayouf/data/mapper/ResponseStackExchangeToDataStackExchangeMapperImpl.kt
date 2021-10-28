package com.mayouf.data.mapper

import com.mayouf.data.entities.data.*
import com.mayouf.data.entities.response.ResponseCollectives
import com.mayouf.data.entities.response.ResponseExternalLinks
import com.mayouf.data.entities.response.ResponseItems
import com.mayouf.data.entities.response.ResponseStackExchange
import javax.inject.Inject

interface ResponseStackExchangeToDataStackExchangeMapper {
    fun toDataModel(responseStackExchange: ResponseStackExchange): DataStackExchange
}

class ResponseStackExchangeToDataStackExchangeMapperImpl @Inject constructor() :
    ResponseStackExchangeToDataStackExchangeMapper {
    override fun toDataModel(responseStackExchange: ResponseStackExchange): DataStackExchange =
        DataStackExchange(
            items = responseStackExchange.items.map { toDataItems(it) },
            hasMore = responseStackExchange.hasMore,
            quotaMax = responseStackExchange.quotaMax,
            quotaRemaining = responseStackExchange.quotaRemaining
        )

    private fun toDataItems(dataItems: ResponseItems): DataItems = DataItems(
        dataBadgeCounts = DataBadgeCounts(
            bronze = dataItems.responseBadgeCounts.bronze,
            silver = dataItems.responseBadgeCounts.silver,
            gold = dataItems.responseBadgeCounts.gold
        ),
        creationDate = dataItems.creationDate ?: 0,
        userId = dataItems.userId ?: 0,
        location = dataItems.location,
        profileImage = dataItems.profileImage ?: "",
        displayName = dataItems.displayName ?: "",
        collectives = dataItems.collectives?.map { it?.let { it1 -> toDatCollectives(it1) } },
        accountId = dataItems.accountId ?: 0,
        isEmployee = dataItems.isEmployee ?: false,
        lastModifiedDate = dataItems.lastModifiedDate ?: 0,
        lastAccessDate = dataItems.lastAccessDate ?: 0,
        reputationChangeYear = dataItems.reputationChangeYear ?: 0,
        reputationChangeQuarter = dataItems.reputationChangeQuarter ?: 0,
        reputationChangeMonth = dataItems.reputationChangeMonth ?: 0,
        reputationChangeWeek = dataItems.reputationChangeWeek ?: 0,
        reputationChangeDay = dataItems.reputationChangeDay ?: 0,
        reputation = dataItems.reputation ?: 0,
        userType = dataItems.userType ?: "",
        acceptRate = dataItems.acceptRate ?: 0,
        websiteUrl = dataItems.websiteUrl ?: "",
        link = dataItems.link ?: ""
    )

    private fun toDatCollectives(responseCollectives: ResponseCollectives): DataCollectives =
        DataCollectives(
            dataCollective = DataCollective(
                tags = responseCollectives.responseCollective.tags,
                dataExternalLinks = responseCollectives.responseCollective.dataExternalLinks.map {
                    toDataExternalLinks(
                        it
                    )
                },
                description = responseCollectives.responseCollective.description,
                link = responseCollectives.responseCollective.link,
                name = responseCollectives.responseCollective.name,
                slug = responseCollectives.responseCollective.slug
            ),
            role = responseCollectives.role
        )

    private fun toDataExternalLinks(responseExternalLinks: ResponseExternalLinks): DataExternalLinks =
        DataExternalLinks(
            type = responseExternalLinks.type,
            link = responseExternalLinks.link
        )

}