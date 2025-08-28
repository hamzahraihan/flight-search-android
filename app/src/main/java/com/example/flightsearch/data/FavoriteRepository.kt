package com.example.flightsearch.data

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.model.FavoriteWithAirports
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavorites(): Flow<List<Favorite>>

    fun getAllFavoriteWithAirports(): Flow<List<FavoriteWithAirports>>

    suspend fun setFavoriteFlight(favorite: Favorite)

    suspend fun deleteFavoriteFlight(departureCode: String, destinationCode: String)

    suspend fun exists(departureCode: String, destinationCode: String): Int
}