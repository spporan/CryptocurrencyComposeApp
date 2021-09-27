package com.poran.cryptocurrencycomposeapp.presentation.coinList

sealed class CoinListState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>: CoinListState<T>()

    class Success<T>(data: T?): CoinListState<T>(data)

    class Error<T>(message: String?): CoinListState<T>(message = message)
}
