package com.poran.cryptocurrencycomposeapp.data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class TeamMember(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: String
)