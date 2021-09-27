package com.poran.cryptocurrencycomposeapp.data.repository

import com.poran.cryptocurrencycomposeapp.data.remote.ApiService
import com.poran.cryptocurrencycomposeapp.data.remote.dto.CoinDetailsDto
import com.poran.cryptocurrencycomposeapp.data.remote.dto.CoinDto
import com.poran.cryptocurrencycomposeapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: ApiService
): CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)
    }
}