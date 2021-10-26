package com.mayouf.data.entities

import com.google.gson.annotations.SerializedName

data class DataBadgeCounts(

    @SerializedName("bronze") val bronze: Int,
    @SerializedName("silver") val silver: Int,
    @SerializedName("gold") val gold: Int
)