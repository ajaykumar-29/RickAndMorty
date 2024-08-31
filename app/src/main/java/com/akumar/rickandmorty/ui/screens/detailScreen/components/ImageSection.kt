package com.akumar.rickandmorty.ui.screens.detailScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.ui.theme.Typography

@Composable
fun ImageSectionLandscape(rickAndMorty: RickAndMorty) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CharacterImage(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxHeight(), imageUrl = rickAndMorty.image
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            CharacterNameText(rickAndMorty)
        }
    }
}

@Composable
fun ImageSectionPortrait(rickAndMorty: RickAndMorty) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CharacterImage(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth(), imageUrl = rickAndMorty.image
        )
        CharacterNameText(rickAndMorty, TextAlign.Center)
    }
}

@Composable
fun CharacterImage(modifier: Modifier, imageUrl: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
            .build(),
        loading = {
            Box(
                contentAlignment = Center, modifier = Modifier.size(100.dp)
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        },
        contentDescription = "rickAndMorty.name",
        modifier = modifier
    )
}

@Composable
fun CharacterNameText(rickAndMorty: RickAndMorty, textAlign: TextAlign? = null) {
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = rickAndMorty.name,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
        fontSize = Typography.headlineSmall.fontSize
    )
    Text(modifier = Modifier.padding(top = 2.dp), text = rickAndMorty.species.uppercase())
}