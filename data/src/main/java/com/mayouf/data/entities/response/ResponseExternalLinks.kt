package com.mayouf.data.entities.response

import com.google.gson.annotations.SerializedName


data class ResponseExternalLinks(

    @SerializedName("type") val type: String,
    @SerializedName("link") val link: String
)