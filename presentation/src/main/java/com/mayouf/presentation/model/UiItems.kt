package com.mayouf.presentation.model

data class UiItems(
    val dataBadgeCounts: UiBadgeCounts,
    val reputation: Int,
    val creationDate: Int,
    val userId: Int,
    val location: String,
    val profileImage: String,
    val displayName: String
)