package com.poran.cryptocurrencycomposeapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.poran.cryptocurrencycomposeapp.domain.models.Coin
import java.io.Serializable

@Keep
data class CoinDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
): Serializable

fun CoinDto.toCoin() = Coin(id = id,isActive = isActive, name = name, rank = rank, symbol = symbol)