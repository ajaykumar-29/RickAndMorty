package com.akumar.rickandmorty.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val colorScheme = lightColorScheme(
    primary = Color.Blue,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.Black
)

@Composable
fun RickAndMortyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}