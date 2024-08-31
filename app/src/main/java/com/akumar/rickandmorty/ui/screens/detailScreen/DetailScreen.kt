package com.akumar.rickandmorty.ui.screens.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.akumar.rickandmorty.ui.screens.detailScreen.components.DetailHeader
import com.akumar.rickandmorty.ui.screens.detailScreen.components.DetailStateWrapper


@Composable
fun RickAndMortyDetailScreen(
    innerPadding: PaddingValues,
    id: Int,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        DetailHeader(navController)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            DetailStateWrapper(id)
        }
    }
}