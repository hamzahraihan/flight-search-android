package com.example.flightsearch.data.local

import com.example.flightsearch.model.Airport
import com.example.flightsearch.data.AirportDao
import com.example.flightsearch.data.AirportRepository
import kotlinx.coroutines.flow.Flow

class LocalAirportDataRepository(private val airportDao: AirportDao) : AirportRepository {
    override fun getFlightBySearch(search: String): Flow<List<Airport>> =
        airportDao.getFlightBySearch(search)

    override fun getAllFlights(): Flow<List<Airport>> = airportDao.getAllFlights()

}