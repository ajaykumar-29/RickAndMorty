package com.akumar.rickandmorty.ui.screens.detailScreen.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.ui.theme.rnkPrimary
import com.akumar.rickandmorty.ui.theme.rnkSecondary

@Composable
fun DetailSection(rickAndMorty: RickAndMorty) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    if (isLandscape) {
        Row(modifier = Modifier.fillMaxSize()) {
            DetailSectionContent(
                rickAndMorty = rickAndMorty,
                upperCardModifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                lowerCardModifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            )
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            DetailSectionContent(
                rickAndMorty = rickAndMorty,
                upperCardModifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                lowerCardModifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            )
        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun DetailSectionContent(
    rickAndMorty: RickAndMorty,
    upperCardModifier: Modifier,
    lowerCardModifier: Modifier
) {
    DetailCard(
        modifier = upperCardModifier,
        cornerDecorationModifier = Modifier
            .fillMaxSize(0.25f)
            .clip(CutCornerShape(topStart = 50.dp, bottomEnd = 50.dp))
            .background(rnkPrimary),
        centerDecorationModifier = Modifier
            .padding(10.dp)
            .clip(CutCornerShape(topStart = 50.dp, bottomEnd = 50.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black, rnkSecondary)
                )
            )
            .border(
                width = 3.dp,
                color = rnkPrimary,
                shape = CutCornerShape(topStart = 50.dp, bottomEnd = 50.dp)
            )

    ) {
        DetailSectionUpper(rickAndMorty)
    }
    Spacer(modifier = Modifier.size(20.dp))
    DetailCard(
        modifier = lowerCardModifier,
        cornerDecorationModifier = Modifier
            .fillMaxSize(0.4f)
            .padding(vertical = 5.dp, horizontal = 8.dp)
            .clip(CutCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
            .background(rnkPrimary),
        centerDecorationModifier = Modifier
            .padding(10.dp)
            .clip(CutCornerShape(topStart = 25.dp, bottomEnd = 25.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black, rnkSecondary)
                )
            )
            .border(
                width = 3.dp,
                color = rnkPrimary,
                shape = CutCornerShape(topStart = 25.dp, bottomEnd = 25.dp)
            )
    ) {
        DetailSectionLower(rickAndMorty)
    }
}