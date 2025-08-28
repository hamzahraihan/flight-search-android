package com.example.flightsearch.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite

@Composable
fun AirportItem(
    departureAirport: Airport,
    destinationAirport: Airport,
    isFavorite: (String, String) -> Boolean,
    onClickFavorite: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    val isFavorite = isFavorite(departureAirport.iataCode, destinationAirport.iataCode)

    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Depart")
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = departureAirport.iataCode, fontWeight = FontWeight.Bold)
                    Text(
                        text = departureAirport.name,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Text("Arrive")
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = destinationAirport.iataCode, fontWeight = FontWeight.Bold)
                    Text(
                        text = destinationAirport.name,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
            IconButton(
                onClick = {
                    onClickFavorite(
                        Favorite(
                            departureCode = departureAirport.iataCode,
                            destinationCode = destinationAirport.iataCode
                        )
                    )
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (isFavorite) Color.Yellow else Color.Gray,
                        modifier = Modifier
                            .size(42.dp)
                    )
                }
            )
        }
    }
}
