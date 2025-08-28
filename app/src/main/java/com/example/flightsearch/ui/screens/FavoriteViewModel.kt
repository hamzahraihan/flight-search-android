package com.example.flightsearch.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.FavoriteRepository
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.model.FavoriteWithAirports
import kotlinx.coroutines.flow.SharingStarted
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

    fun isFavorite(departureCode: String, destinationCode: String): Boolean {
        return uiState.value.favoriteAirport.any { favorites ->
            favorites.departureAirport.iataCode == departureCode && favorites.destinationAirport.iataCode == destinationCode
        }
    }

    fun updateFavoriteFlight(favorite: Favorite) = viewModelScope.launch {
        val exists =
            favoriteRepository.exists(favorite.departureCode, favorite.destinationCode) > 0
        if (!exists) {
            addFavorite(favorite)
        } else {
            deleteFavorite(
                departureCode = favorite.departureCode,
                destinationCode = favorite.destinationCode
            )
        }
    }

    private fun addFavorite(favorite: Favorite) = viewModelScope.launch {
        favoriteRepository.setFavoriteFlight(favorite)
    }

    private fun deleteFavorite(departureCode: String, destinationCode: String) =
        viewModelScope.launch {
            favoriteRepository.deleteFavoriteFlight(departureCode, destinationCode)
        }
}

data class FavoriteUiState(
    val favoriteAirport: List<FavoriteWithAirports> = emptyList()
)
