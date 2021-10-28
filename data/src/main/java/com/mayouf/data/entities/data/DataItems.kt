package com.mayouf.data.entities.data

import com.google.gson.annotations.SerializedName

data class DataItems(
    val dataBadgeCounts: DataBadgeCounts,
    val collectives: List<DataCollectives?>?,
    val accountId: Int,
    val isEmployee: Boolean,
    val lastModifiedDate: Int,
    val lastAccessDate: Int,
    val reputationChangeYear: Int,
    val reputationChangeQuarter: Int,
    val reputationChangeMonth: Int,
    val reputationChangeWeek: Int,
    val reputationChangeDay: Int,
    val reputation: Int,
    val creationDate: Int,
    val userType: String,
    val userId: Int,
    val acceptRate: Int,
    val location: String?,
    val websiteUrl: String,
    val link: String,
    val profileImage: String,
    val displayName: String
)