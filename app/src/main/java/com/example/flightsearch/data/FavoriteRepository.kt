package com.example.flightsearch.data

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavorites(): Flow<List<Favorite>>
    suspend fun setFavoriteFlight(airport: Airport)
}