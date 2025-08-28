package com.example.flightsearch.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.ui.widgets.AirportItem

@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    isFavorite: (String, String) -> Boolean,
    onClickFavorite: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Favorite Flights", fontWeight = FontWeight.Bold)
        if (uiState.favoriteAirport.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null)
                Text("You have no favorite flight", textAlign = TextAlign.Center)
            }
        } else {
            FavoriteList(
                uiState = uiState,
                isFavorite = isFavorite,
                onClickFavorite = onClickFavorite,
            )
        }
    }
}

@Composable
fun FavoriteList(
    uiState: FavoriteUiState,
    isFavorite: (String, String) -> Boolean,
    onClickFavorite: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(uiState.favoriteAirport) { favorite ->
            AirportItem(
                departureAirport = favorite.departureAirport,
                destinationAirport = favorite.destinationAirport,
                isFavorite = isFavorite,
                onClickFavorite = onClickFavorite,
            )
        }
    }
}

