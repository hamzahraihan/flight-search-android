package com.example.flightsearch.data.local

import com.example.flightsearch.model.Favorite
import com.example.flightsearch.data.FavoriteDao
import com.example.flightsearch.data.FavoriteRepository
import com.example.flightsearch.model.FavoriteWithAirports
import kotlinx.coroutines.flow.Flow

class LocalFavoriteData(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun getAllFavorites(): Flow<List<Favorite>> = favoriteDao.getAllFavorites()

    override fun getAllFavoriteWithAirports(): Flow<List<FavoriteWithAirports>> =
        favoriteDao.getAllFavoriteWithAirports()

    override suspend fun setFavoriteFlight(favorite: Favorite) =
        favoriteDao.setFavoriteFlight(favorite = favorite)

    override suspend fun deleteFavoriteFlight(favorite: Favorite) =
        favoriteDao.deleteFavorite(favorite)

    override suspend fun exists(departureCode: String, destinationCode: String): Int =
        favoriteDao.exists(departureCode, destinationCode)
}