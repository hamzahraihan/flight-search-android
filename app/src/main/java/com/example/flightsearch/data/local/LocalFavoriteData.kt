package com.example.flightsearch.data.local

import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.data.FavoriteDao
import com.example.flightsearch.data.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class LocalFavoriteData(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun getAllFavorites(): Flow<List<Favorite>> = favoriteDao.getAllFavorites()

    override suspend fun setFavoriteFlight(favorite: Favorite) =
        favoriteDao.setFavoriteFlight(favorite = favorite)
}