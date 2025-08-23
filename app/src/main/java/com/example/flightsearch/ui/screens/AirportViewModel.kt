package com.example.flightsearch.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.model.Airport
import com.example.flightsearch.data.AirportRepository
import com.example.flightsearch.data.UserPreferencesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(FlowPreview::class)
class AirportViewModel(
    private val airportRepository: AirportRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(AirportUiState())


    val uiState: StateFlow<AirportUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferencesRepository.searchInput
                .collectLatest { input ->
                    if (_uiState.value.searchInput.isEmpty()) {
                        _uiState.update { it.copy(searchInput = input) }
                        getFlightBySearch(input = input)
                    }
                }
        }
    }

//    fun getAllFlights() {
//        viewModelScope.launch {
//            airportRepository.getAllFlights().collect { fligths ->
//                _uiState.update { it.copy(airportList = fligths) }
//            }
//        }
//    }


    fun updateUserInput(input: String) {
        _uiState.update { it.copy(searchInput = input) }

        getFlightBySearch(input)

        viewModelScope.launch {
            userPreferencesRepository.saveSearchInput(input)
        }
    }

    private fun getSearchAutocomplete(input: String) {
        viewModelScope.launch {
            airportRepository.getFlightBySearch("%$input%").filterNotNull().collect { airports ->
                _uiState.update {
                    Log.e("FROM DATABASE", airports.toString())
                    it.copy(airportList = airports)
                }
            }
        }
    }

    private fun getFlightBySearch(input: String) {
        viewModelScope.launch {
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


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class AirportUiState(
    val searchInput: String = "", val airportList: List<Airport> = emptyList()
)