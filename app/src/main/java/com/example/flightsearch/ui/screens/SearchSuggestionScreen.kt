package com.example.flightsearch.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SearchResultScreen(
    viewModel: AirportViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier) {
        SearchList(
            viewModel = viewModel,
            uiState = uiState,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        )
    }
}

@Composable
private fun SearchList(
    viewModel: AirportViewModel,
    uiState: AirportUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(uiState.suggestedAirport) { airport ->
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .clickable {
                        viewModel.updateCurrentAirport(airport.iataCode)
                        viewModel.updateAirportByDestination(iataCode = airport.iataCode)
                        Log.d("FROM CURRENT AIRPORT", uiState.currentAirport.toString())
                    },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = airport.iataCode, fontWeight = FontWeight.Bold)
                Text(text = airport.name)
            }
        }
    }
}