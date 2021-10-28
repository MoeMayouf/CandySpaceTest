package com.mayouf.data.entities.response

import com.google.gson.annotations.SerializedName

data class ResponseBadgeCounts(

    @SerializedName("bronze") val bronze: Int,
    @SerializedName("silver") val silver: Int,
    @SerializedName("gold") val gold: Int
)