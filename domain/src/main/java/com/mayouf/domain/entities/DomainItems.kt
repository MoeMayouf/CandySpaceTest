package com.mayouf.domain.entities

data class DomainItems(
    val dataBadgeCounts: DomainBadgeCounts,
    val reputation: Int,
    val creationDate: Int,
    val userId: Int,
    val location: String,
    val profileImage: String,
    val displayName: String
)