package com.mayouf.data.entities

import com.google.gson.annotations.SerializedName


data class DataCollective(

    @SerializedName("tags") val tags: List<String>,
    @SerializedName("external_links") val dataExternalLinks: List<DataExternalLinks>,
    @SerializedName("description") val description: String,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String
)