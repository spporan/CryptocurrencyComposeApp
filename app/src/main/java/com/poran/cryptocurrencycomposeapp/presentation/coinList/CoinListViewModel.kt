package com.poran.cryptocurrencycomposeapp.presentation.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poran.cryptocurrencycomposeapp.common.Resource
import com.poran.cryptocurrencycomposeapp.domain.models.Coin
import com.poran.cryptocurrencycomposeapp.domain.usecases.getCoins.GetCoinsUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUserCase: GetCoinsUserCase
): ViewModel() {

    private val _state = mutableStateOf<CoinListState<List<Coin>>>(CoinListState.Loading())
    val state: State<CoinListState<List<Coin>>> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUserCase().onEach { resource ->
            when(resource) {
                is Resource.Error -> {
                    _state.value = CoinListState.Error(resource.message?:"UnExpected Error")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState.Loading()
                }
                is Resource.Success -> {
                    _state.value = CoinListState.Success(resource.data)
                }
            }

        }.launchIn(viewModelScope)
    }
}