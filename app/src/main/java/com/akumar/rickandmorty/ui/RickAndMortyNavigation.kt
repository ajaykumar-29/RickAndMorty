package com.akumar.rickandmorty.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akumar.rickandmorty.ui.screens.detailScreen.RickAndMortyDetailScreen
import com.akumar.rickandmorty.ui.screens.listScreen.RickAndMortyListScreen

@Composable
fun RickAndMortyApiNavigation(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "rickAndMorty_list_screen"
    ) {
        composable("rickAndMorty_list_screen") {
            RickAndMortyListScreen(innerPadding, navController)
        }
        composable(
            "rickAndMorty_detail_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = remember {
                it.arguments?.getInt("id")
            }
            if (id != null) {
                RickAndMortyDetailScreen(innerPadding = innerPadding, id = id, navController)
            }
        }
    }
}