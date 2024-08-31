package com.akumar.rickandmorty.ui.screens.listScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akumar.rickandmorty.ui.screens.listScreen.ListScreenViewModel

@Composable
fun RickAndMortyList(
    navController: NavController,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    // Collecting state once and using it
    val rickAndMortyList by viewModel.rickAndMortyList.collectAsState()
    val endReached by viewModel.endReached.collectAsState()
    val loadError by viewModel.loadError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Use derivedStateOf for columns calculation
    val columns by remember(screenWidth) {
        derivedStateOf {
            if (screenWidth < 600.dp) 2 else 4
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(rickAndMortyList.size) { index ->
            if (index >= rickAndMortyList.size - 1 && !endReached && !isLoading) {
                LaunchedEffect(Unit) {
                    viewModel.loadRickAndMortyPaginated()
                }
            }
            RickAndMortyCard(
                rickAndMorty = rickAndMortyList[index],
                navController = navController,
            )
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        if (loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadRickAndMortyPaginated()
            }
        }
    }
}