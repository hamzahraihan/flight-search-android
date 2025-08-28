package com.example.flightsearch.model

import androidx.room.Embedded
import androidx.room.Relation


data class FavoriteWithAirports(
    @Embedded val favorite: Favorite,

    @Relation(
        parentColumn = "departure_code",
        entityColumn = "iata_code"
    )
    val departureAirport: Airport,

    @Relation(
        parentColumn = "destination_code",
        entityColumn = "iata_code"
    )
    val destinationAirport: Airport
)