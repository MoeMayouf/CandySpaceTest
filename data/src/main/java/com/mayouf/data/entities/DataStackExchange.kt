package com.mayouf.data.entities

import com.google.gson.annotations.SerializedName

data class DataStackExchange(

    @SerializedName("items") val items: List<DataItems>,
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("quota_max") val quotaMax: Int,
    @SerializedName("quota_remaining") val quotaRemaining: Int
)