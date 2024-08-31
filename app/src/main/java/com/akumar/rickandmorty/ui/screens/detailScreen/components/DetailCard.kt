package com.akumar.rickandmorty.ui.screens.detailScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DetailCard(
    modifier: Modifier = Modifier,
    cornerDecorationModifier: Modifier = Modifier,
    centerDecorationModifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        Box(modifier = cornerDecorationModifier.align(Alignment.TopEnd))
        Box(modifier = cornerDecorationModifier.align(Alignment.BottomStart))
        Box(modifier = centerDecorationModifier.fillMaxSize()) {
            content()
        }
    }
}