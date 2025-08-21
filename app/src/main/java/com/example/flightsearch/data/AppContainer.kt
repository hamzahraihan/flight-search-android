package com.example.flightsearch.data

import android.content.Context
import com.example.flightsearch.data.local.LocalAirportDataRepository
import com.example.flightsearch.data.local.LocalFavoriteDataRepository

interface AppContainer {
    val airportRepository: AirportRepository
    val favoriteRepository: FavoriteRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val airportRepository: AirportRepository by lazy {
        LocalAirportDataRepository(AppDatabase.getDatabase(context).airportDao())
    }

    override val favoriteRepository: FavoriteRepository by lazy {
        LocalFavoriteDataRepository(AppDatabase.getDatabase(context).favoriteDao())
    }
}