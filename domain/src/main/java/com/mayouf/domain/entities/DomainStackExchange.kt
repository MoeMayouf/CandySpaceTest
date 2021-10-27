package com.mayouf.domain.entities


data class DomainStackExchange(
    val items: List<DomainItems>,
    val hasMore: Boolean,
    val quotaMax: Int,
    val quotaRemaining: Int
)