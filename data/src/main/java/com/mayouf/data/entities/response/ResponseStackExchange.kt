package com.mayouf.data.entities.response

import com.google.gson.annotations.SerializedName

data class ResponseStackExchange(

    @SerializedName("items") val items: List<ResponseItems>,
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("quota_max") val quotaMax: Int,
    @SerializedName("quota_remaining") val quotaRemaining: Int
)