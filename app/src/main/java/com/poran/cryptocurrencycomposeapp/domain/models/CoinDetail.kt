package com.poran.cryptocurrencycomposeapp.domain.models

import com.poran.cryptocurrencycomposeapp.data.remote.dto.TeamMember

data class CoinDetail(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val description: String,
    val tags: List<String>,
    val team: List<TeamMember>,
    val rank: Int,
    val symbol: String,
)
