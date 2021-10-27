package com.mayouf.presentation.model

data class UiStackExchange(
    val items: List<UiItems>,
    val hasMore: Boolean,
    val quotaMax: Int,
    val quotaRemaining: Int
)