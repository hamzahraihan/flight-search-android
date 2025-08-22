package com.example.flightsearch.data

import android.content.Context
import com.example.flightsearch.data.local.LocalAirportData
import com.example.flightsearch.data.local.LocalFavoriteData

interface AppContainer {
    val airportRepository: AirportRepository
    val favoriteRepository: FavoriteRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val airportRepository: AirportRepository by lazy {
        LocalAirportData(AppDatabase.getDatabase(context).airportDao())
    }

    override val favoriteRepository: FavoriteRepository by lazy {
        LocalFavoriteData(AppDatabase.getDatabase(context).favoriteDao())
    }
}