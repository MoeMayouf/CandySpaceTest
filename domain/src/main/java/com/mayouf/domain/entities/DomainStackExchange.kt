package com.mayouf.domain.entities

import com.mayouf.data.entities.DataItems

data class DomainStackExchange(
    val items: List<DataItems>,
    val hasMore: Boolean,
    val quotaMax: Int,
    val quotaRemaining: Int
)