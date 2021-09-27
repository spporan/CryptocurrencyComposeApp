package com.poran.cryptocurrencycomposeapp.domain.usecases.getCoins

import android.util.Log
import com.poran.cryptocurrencycomposeapp.common.Resource
import com.poran.cryptocurrencycomposeapp.data.remote.dto.toCoin
import com.poran.cryptocurrencycomposeapp.domain.models.Coin
import com.poran.cryptocurrencycomposeapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
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
            Log.d("COIN","COIN: $coins")
            emit(Resource.Success(coins.map { it.toCoin() }))
        }
        catch (ex: HttpException) {
            Log.d("COIN","error: ${ex.message()}")

            emit(Resource.Error<List<Coin>>(ex.localizedMessage?:"UnExcepted error occurred!", ))
        }
        catch (e: IOException) {
            Log.d("COIN","error: ${e.localizedMessage}")
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Please check your internet connection" ))
        }
        catch (e: Exception) {
            Log.d("COIN","error: ${e.localizedMessage}")
        }
    }
}