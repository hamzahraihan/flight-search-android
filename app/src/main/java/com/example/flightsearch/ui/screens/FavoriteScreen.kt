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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.flightsearch.model.Favorite

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onClickFavorite: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

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
            LazyColumn {
                items(uiState.favoriteAirport) { favorite ->
                    Text(favorite.departureCode)
                    Text(favorite.destinationCode)
                }
            }
        }
    }
}

