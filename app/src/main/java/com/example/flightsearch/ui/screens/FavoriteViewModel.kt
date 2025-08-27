package com.example.flightsearch.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.FavoriteRepository
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.model.FavoriteWithAirports
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    val uiState = favoriteRepository.getAllFavoriteWithAirports().filterNotNull().map {
        FavoriteUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = FavoriteUiState()
    )

    suspend fun isFavorite(departureCode: String, destinationCode: String): Boolean {
        val favorites = favoriteRepository.getAllFavorites().first()
        return favorites.any { favorite ->
            favorite.departureCode == departureCode && favorite.destinationCode == destinationCode
        }
    }

    fun updateFavoriteFlight(favorite: Favorite) = viewModelScope.launch {
        val exists =
            favoriteRepository.exists(favorite.departureCode, favorite.destinationCode) > 0
        if (!exists) {
            addFavorite(favorite)
        }
    }

    private fun addFavorite(favorite: Favorite) = viewModelScope.launch {
        favoriteRepository.setFavoriteFlight(favorite)
    }

    private fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {
        favoriteRepository.deleteFavoriteFlight(favorite)
    }
}

data class FavoriteUiState(
    val favoriteAirport: List<FavoriteWithAirports> = emptyList()
)
