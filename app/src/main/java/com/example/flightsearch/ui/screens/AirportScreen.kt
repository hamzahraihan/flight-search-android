package com.example.flightsearch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.room.util.convertByteToUUID
import com.example.flightsearch.model.Airport

@Composable
fun AirportScreen(uiState: AirportUiState, modifier: Modifier) {
    val airportList: List<Airport> = uiState.airportList
    val currentAirport: Airport? = uiState.currentAirport

    Column(modifier = modifier) {
        Text("Flights from ${airportList.first().iataCode}")
        AirportList(airportList = airportList, currentAirport)
    }
}

@Composable
fun AirportList(
    airportList: List<Airport>,
    currentAirport: Airport?,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(airportList) {
            Text(currentAirport!!.iataCode)
            Text(it.iataCode)
        }
    }
}