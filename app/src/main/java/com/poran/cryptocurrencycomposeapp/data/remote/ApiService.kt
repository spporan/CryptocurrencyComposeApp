package com.poran.cryptocurrencycomposeapp.data.remote

import com.poran.cryptocurrencycomposeapp.data.remote.dto.CoinDetailsDto
import com.poran.cryptocurrencycomposeapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiService {
    @GET("v1/coins/")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto

}