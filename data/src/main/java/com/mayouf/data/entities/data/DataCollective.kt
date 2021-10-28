package com.mayouf.data.entities.data

import com.google.gson.annotations.SerializedName


data class DataCollective(

    val tags: List<String>,
    val dataExternalLinks: List<DataExternalLinks>,
    val description: String,
    val link: String,
    val name: String,
    val slug: String
)