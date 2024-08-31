package com.akumar.rickandmorty.ui.screens.detailScreen.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akumar.rickandmorty.R
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.ui.theme.Typography
import com.akumar.rickandmorty.ui.theme.rnkFemaleColor
import com.akumar.rickandmorty.ui.theme.rnkMaleColor

@Composable
fun BoxScope.DetailSectionUpper(rickAndMorty: RickAndMorty) {

    var aliveIconColor by remember { mutableStateOf(Color.Gray) }
    var genderIcon by remember { mutableIntStateOf(R.drawable.gender_male) }
    var genderColor by remember { mutableStateOf(Color.Unspecified) }

    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    LaunchedEffect(Unit) {
        aliveIconColor = when (rickAndMorty.status) {
            "Alive" -> Color.Green
            "Dead" -> Color.Red
            else -> Color.Gray
        }
        genderIcon =
            if (rickAndMorty.gender == "Male") R.drawable.gender_male else R.drawable.gender_female
        genderColor = if (rickAndMorty.gender == "Male") rnkMaleColor else rnkFemaleColor
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopStart)
            .padding(25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = rickAndMorty.status, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp),
                fontSize = Typography.titleLarge.fontSize
            )
            Icon(
                painterResource(R.drawable.alive),
                contentDescription = "",
                modifier = Modifier.size(12.dp),
                tint = aliveIconColor
            )
        }

        Text(
            text = rickAndMorty.id.toString(), fontWeight = FontWeight.Bold,
            fontSize = Typography.titleLarge.fontSize
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(0.67f)
            .align(Center)
    ) {
        if (isLandscape) ImageSectionLandscape(rickAndMorty) else ImageSectionPortrait(rickAndMorty)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
            .align(Alignment.BottomStart),
    ) {
        Text(rickAndMorty.gender, fontWeight = FontWeight.Bold)
        if (rickAndMorty.gender != "unknown") {
            Icon(
                painter = painterResource(genderIcon),
                contentDescription = "",
                tint = genderColor
            )
        }
    }
}