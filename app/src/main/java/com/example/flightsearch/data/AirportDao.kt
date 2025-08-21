package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import com.example.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query(
        """
        SELECT * from airport WHERE iata_code = :search AND name = :search ORDER BY passengers ASC
    """
    )
    fun getFlightBySearch(search: String): Flow<List<Airport>>

    @Query(
        """
        SELECT * from airport ORDER BY passengers ASC
    """
    )
    fun getAllFlights(): Flow<List<Airport>>
}