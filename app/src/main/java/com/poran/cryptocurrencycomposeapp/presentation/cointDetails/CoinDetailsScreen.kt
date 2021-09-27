package com.poran.cryptocurrencycomposeapp.presentation.cointDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.poran.cryptocurrencycomposeapp.data.remote.dto.TeamMember
import com.poran.cryptocurrencycomposeapp.presentation.cointDetails.components.CoinTags
import com.poran.cryptocurrencycomposeapp.presentation.cointDetails.components.TeamListItem

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is CoinDetailsState.Error -> {
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
            is CoinDetailsState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
            is CoinDetailsState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    state.data?.let { coinDetails ->
                       item {
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Text(
                                   text = "${coinDetails.rank}. ${coinDetails.name} (${coinDetails.symbol})",
                                   style = MaterialTheme.typography.h4,
                                   overflow = TextOverflow.Ellipsis,
                                   modifier = Modifier.weight(8f)
                               )
                               Text(
                                   text = if (coinDetails.isActive) "active" else "inactive",
                                   modifier = Modifier
                                       .align(Alignment.CenterVertically)
                                       .weight(2f),
                                   color = if (coinDetails.isActive) Color.Green else Color.Red,
                                   fontStyle = FontStyle.Italic,
                                   textAlign = TextAlign.End,
                                   style = MaterialTheme.typography.body2,
                               )
                           }
                           Spacer(
                               modifier = Modifier.height(15.dp)
                           )
                           Text(
                               text = coinDetails.description,
                               style = MaterialTheme.typography.body2,
                               overflow = TextOverflow.Ellipsis,
                           )
                           Spacer(
                               modifier = Modifier.height(15.dp)
                           )
                           Text(
                               text = "Tags",
                               style = MaterialTheme.typography.h4,
                           )
                           Spacer(
                               modifier = Modifier.height(15.dp)
                           )
                           FlowRow(
                               mainAxisSpacing = 10.dp,
                               crossAxisSpacing = 10.dp,
                               modifier = Modifier.fillMaxWidth()
                           ) {
                               coinDetails.tags.forEach { tag ->
                                   CoinTags(tag = tag)
                               }

                           }
                           Spacer(
                               modifier = Modifier.height(15.dp)
                           )
                           Text(
                               text = "Team Members",
                               style = MaterialTheme.typography.h4,
                           )
                           Spacer(
                               modifier = Modifier.height(15.dp)
                           )
                       }
                        items(coinDetails.team) { item: TeamMember ->  
                            TeamListItem(
                                teamMember = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                            Divider()
                        }
                    }
                }
            }
        }
    }

}