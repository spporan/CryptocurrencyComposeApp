package com.poran.cryptocurrencycomposeapp.domain.usecases.getCoins

import com.poran.cryptocurrencycomposeapp.common.Resource
import com.poran.cryptocurrencycomposeapp.data.remote.dto.toCoin
import com.poran.cryptocurrencycomposeapp.domain.models.Coin
import com.poran.cryptocurrencycomposeapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

import javax.inject.Inject

class GetCoinsUserCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins()
            emit(Resource.Success(coins.map { it.toCoin() }))
        }
        catch (ex: HttpException) {
            emit(Resource.Error<List<Coin>>(ex.localizedMessage?:"UnExcepted error occurred!", ))
        }
        catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Please check your internet connection" ))
        }
    }
}