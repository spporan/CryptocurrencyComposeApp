package com.poran.cryptocurrencycomposeapp.data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Stats(
    @SerializedName("contributors")
    val contributors: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("stars")
    val stars: Int,
    @SerializedName("subscribers")
    val subscribers: Int
)