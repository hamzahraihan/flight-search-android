package com.example.flightsearch.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.FavoriteRepository
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteUiState())

    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    init {
        getFavoriteFlights()
    }

    fun getFavoriteFlights() {
        viewModelScope.launch {
            favoriteRepository.getAllFavorites().collect { favorites ->
                _uiState.update {
                    it.copy(favorites)
                }
            }
        }
    }

    suspend fun setFavoriteFligth(airport: Airport) {
        favoriteRepository.setFavoriteFlight(airport = airport)
    }
}

data class FavoriteUiState(
    val favoriteAirport: List<Favorite> = emptyList()
)
