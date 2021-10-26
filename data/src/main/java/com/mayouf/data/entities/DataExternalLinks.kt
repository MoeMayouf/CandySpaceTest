package com.mayouf.data.entities

import com.google.gson.annotations.SerializedName


data class DataExternalLinks(

    @SerializedName("type") val type: String,
    @SerializedName("link") val link: String
)