package com.poran.cryptocurrencycomposeapp.presentation.coinList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.poran.cryptocurrencycomposeapp.domain.models.Coin
import com.poran.cryptocurrencycomposeapp.presentation.ScreenRoute
import com.poran.cryptocurrencycomposeapp.presentation.coinList.components.CoinListItem

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is CoinListState.Error -> {
                Text(
                    text = state.message ?: "UnExpected error occurred!",
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.Center)
                )
            }
            is CoinListState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
            is CoinListState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    state.data?.let {
                        items(it) { item: Coin ->
                            CoinListItem(coin = item, itemClick = {
                                navController.navigate(ScreenRoute.CoinDetailsScreen.route + "/${item.id}")

                            })
                        }
                    }
                }
            }
        }
    }
    
}