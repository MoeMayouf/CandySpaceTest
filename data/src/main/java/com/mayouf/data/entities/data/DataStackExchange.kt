package com.mayouf.data.entities.data

data class DataStackExchange(

    val items: List<DataItems>,
    val hasMore: Boolean,
    val quotaMax: Int,
    val quotaRemaining: Int
)