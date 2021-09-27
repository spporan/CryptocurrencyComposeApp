package com.poran.cryptocurrencycomposeapp.domain.repository

import com.poran.cryptocurrencycomposeapp.data.remote.dto.CoinDetailsDto
import com.poran.cryptocurrencycomposeapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailsDto

}