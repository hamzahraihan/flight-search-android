package com.example.flightsearch.ui.screens

import android.util.Log
import android.view.RoundedCorner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.AppDataContainer
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.theme.FlightSearchTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    airportViewModel: AirportViewModel = viewModel(factory = AppViewModelProvider.Factory),
    favoriteViewModel: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val airportUiState by airportViewModel.uiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Flight Search", color = Color.White)
            }, colors = TopAppBarDefaults.topAppBarColors(Color.Blue)
        )
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            TextField(
                value = airportUiState.searchInput,
                onValueChange = { newValue ->
                    airportViewModel.updateUserInput(newValue)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            if (airportUiState.searchInput.isNotEmpty() && airportUiState.currentAirport == null) {
                SearchResultScreen(
                    viewModel = airportViewModel,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            if (airportUiState.searchInput.isEmpty() && airportUiState.currentAirport == null) {
                FavoriteScreen(
                    viewModel = favoriteViewModel,
                    onClickFavorite = { favorite ->
                        Log.d("FAVORITE ON CLICK ", favorite.toString())
                        coroutineScope.launch {
                            favoriteViewModel.setFavoriteFligth(
                                favorite
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                )
            }

            if (airportUiState.currentAirport != null) {
                AirportScreen(
                    uiState = airportUiState,
                    isFavorite = { departureCode, destinationCode ->
                        favoriteViewModel.isFavorite(departureCode, destinationCode)
                    },
                    onClickFavorite = { favorite ->
                        Log.d("FAVORITE ON CLICK ", favorite.toString())
                        coroutineScope.launch {
                            favoriteViewModel.setFavoriteFligth(
                                favorite
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    FlightSearchTheme {
        HomeScreen()
    }
}