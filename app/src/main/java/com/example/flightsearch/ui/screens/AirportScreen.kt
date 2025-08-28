package com.example.flightsearch.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.ui.widgets.AirportItem

@Composable
fun AirportScreen(
    uiState: AirportUiState,
    isFavorite: (String, String) -> Boolean,
    onClickFavorite: (Favorite) -> Unit,
    modifier: Modifier
) {
    val airportList: List<Airport> = uiState.airportList
    val currentAirport: Airport? = uiState.currentAirport

    Column(modifier = modifier) {
        Text(
            "Flights from ${currentAirport!!.iataCode}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        AirportList(
            airportList = airportList,
            isFavorite = isFavorite,
            onClickFavorite = {
                onClickFavorite(it)
            },
            currentAirport = currentAirport
        )
    }
}

@Composable
fun AirportList(
    airportList: List<Airport>,
    isFavorite: (String, String) -> Boolean,
    currentAirport: Airport,
    onClickFavorite: (Favorite) -> Unit?,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(airportList) { airport ->
            AirportItem(
                departureAirport = currentAirport,
                destinationAirport = airport,
                isFavorite = isFavorite,
                onClickFavorite = {
                    onClickFavorite(it)
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
