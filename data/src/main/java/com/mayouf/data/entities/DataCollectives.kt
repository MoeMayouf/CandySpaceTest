package com.mayouf.data.entities

import com.google.gson.annotations.SerializedName

data class DataCollectives(

    @SerializedName("collective") val dataCollective: DataCollective,
    @SerializedName("role") val role: String
)