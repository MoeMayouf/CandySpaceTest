package com.mayouf.data.entities.response

import com.google.gson.annotations.SerializedName

data class ResponseCollectives(

    @SerializedName("collective") val responseCollective: ResponseCollective,
    @SerializedName("role") val role: String
)