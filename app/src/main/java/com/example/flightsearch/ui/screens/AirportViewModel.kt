package com.example.flightsearch.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.model.Airport
import com.example.flightsearch.data.AirportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AirportViewModel(
    private val airportRepository: AirportRepository,
) : ViewModel() {
    private var _uiState = MutableStateFlow(AirportUiState())


    val uiState: StateFlow<AirportUiState> = _uiState.asStateFlow()

    init {
        getAllFlights()
    }

    fun getAllFlights() {
        viewModelScope.launch {
            airportRepository.getAllFlights().collect { fligths ->
                _uiState.update { it.copy(airportList = fligths) }
            }
        }
    }


//    val airportAllFlights: StateFlow<AirportUiState> =
//        airportRepository.getAllFlights()
//            .map { AirportUiState(airportList = it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = AirportUiState()
//            )


    fun updateUserInput(input: String) {
        _uiState.update { it.copy(searchInput = input) }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class AirportUiState(
    val searchInput: String = "",
    val airportList: List<Airport> = emptyList()
)