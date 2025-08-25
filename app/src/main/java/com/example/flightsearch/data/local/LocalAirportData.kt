package com.example.flightsearch.data.local

import com.example.flightsearch.model.Airport
import com.example.flightsearch.data.AirportDao
import com.example.flightsearch.data.AirportRepository
import kotlinx.coroutines.flow.Flow

class LocalAirportData(private val airportDao: AirportDao) : AirportRepository {
    override fun getFlightBySearch(search: String): Flow<List<Airport>> =
        airportDao.getFlightBySearch(search)

    override fun getFlightByCode(iataCode: String): Flow<Airport> =
        airportDao.getFlightByCode(iataCode)

    override fun getAllFlightsWithDestination(iataCode: String): Flow<List<Airport>> =
        airportDao.getAllFlightsWithDestination(iataCode)

}