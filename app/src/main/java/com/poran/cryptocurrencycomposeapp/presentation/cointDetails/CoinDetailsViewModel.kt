package com.poran.cryptocurrencycomposeapp.presentation.cointDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poran.cryptocurrencycomposeapp.common.Constants
import com.poran.cryptocurrencycomposeapp.common.Resource
import com.poran.cryptocurrencycomposeapp.domain.models.CoinDetail
import com.poran.cryptocurrencycomposeapp.domain.usecases.getCoin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf<CoinDetailsState<CoinDetail>>(CoinDetailsState.Loading())
    val state: State<CoinDetailsState<CoinDetail>> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getDetail(it)
        }
    }

    private fun getDetail(coinId: String) {
        getCoinUseCase(coinId).onEach { resource ->
            when(resource) {
                is Resource.Error -> {
                    _state.value = CoinDetailsState.Error(resource.message)
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailsState.Loading()
                }
                is Resource.Success -> {
                    _state.value = CoinDetailsState.Success(resource.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}