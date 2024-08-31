package com.akumar.rickandmorty.ui.screens.detailScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.ui.theme.Typography
import com.akumar.rickandmorty.ui.theme.rnkPrimary
import com.akumar.rickandmorty.ui.theme.rnkSecondary

@Composable
fun BoxScope.DetailSectionLower(rickAndMorty: RickAndMorty) {

    Row(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, rnkPrimary, RoundedCornerShape(5.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black, rnkSecondary)
                )
            )
            .align(Alignment.TopEnd)
            .padding(horizontal = 8.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Episodes",
            color = Color.White,
            fontSize = Typography.labelMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = rickAndMorty.episode.size.toString(),
            color = Color.White,
            fontSize = Typography.labelMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Origin", color = Color.White, fontWeight = FontWeight.Bold)
        Text(text = rickAndMorty.origin.name, color = Color.White)
        Text(text = "Location", color = Color.White, fontWeight = FontWeight.Bold)
        Text(text = rickAndMorty.location.name, color = Color.White)
    }
}