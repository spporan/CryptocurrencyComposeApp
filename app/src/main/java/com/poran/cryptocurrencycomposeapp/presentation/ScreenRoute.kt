package com.poran.cryptocurrencycomposeapp.presentation

sealed class ScreenRoute(val route: String) {
    object CoinListScreen: ScreenRoute("CoinListScreen")

    object CoinDetailsScreen: ScreenRoute("CoinDetailsScreen")
}
