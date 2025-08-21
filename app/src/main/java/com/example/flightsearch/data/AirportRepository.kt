package com.example.flightsearch.data

import com.example.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow

interface AirportRepository {

    fun getFlightBySearch(search: String): Flow<List<Airport>>

    fun getAllFlights(): Flow<List<Airport>>
}