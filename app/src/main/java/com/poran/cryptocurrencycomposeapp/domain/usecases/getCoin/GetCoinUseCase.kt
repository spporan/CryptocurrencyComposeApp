package com.poran.cryptocurrencycomposeapp.domain.usecases.getCoin

import com.poran.cryptocurrencycomposeapp.common.Resource
import com.poran.cryptocurrencycomposeapp.data.remote.dto.toCoinDetail
import com.poran.cryptocurrencycomposeapp.data.repository.CoinRepositoryImpl
import com.poran.cryptocurrencycomposeapp.domain.models.CoinDetail
import com.poran.cryptocurrencycomposeapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())

            val coinDetail = repository.getCoinById(coinId)

            emit(Resource.Success(coinDetail.toCoinDetail()))

        } catch (e: IOException) {

            emit(Resource.Error<CoinDetail>("Couldn't reach server. Please check your internet connection"))

        }
        catch (e: HttpException) {

            emit(Resource.Error<CoinDetail>(e.localizedMessage?:"UnExcepted error occurred!"))

        }
    }
}