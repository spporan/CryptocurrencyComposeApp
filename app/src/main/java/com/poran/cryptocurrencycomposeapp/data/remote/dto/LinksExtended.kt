package com.poran.cryptocurrencycomposeapp.data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LinksExtended(
    @SerializedName("stats")
    val stats: Stats,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)