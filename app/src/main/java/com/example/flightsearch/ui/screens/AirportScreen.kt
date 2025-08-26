package com.example.flightsearch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.ui.widgets.AirportList

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
