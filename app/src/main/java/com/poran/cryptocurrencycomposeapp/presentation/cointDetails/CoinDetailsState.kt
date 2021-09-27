package com.poran.cryptocurrencycomposeapp.presentation.cointDetails

sealed class CoinDetailsState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(): CoinDetailsState<T>()

    class Success<T>(data: T?): CoinDetailsState<T>(data = data)

    class Error<T>(message: String?): CoinDetailsState<T>(message = message)

}
