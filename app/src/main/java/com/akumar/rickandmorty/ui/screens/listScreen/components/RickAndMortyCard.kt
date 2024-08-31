package com.akumar.rickandmorty.ui.screens.listScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.ui.screens.listScreen.ListScreenViewModel
import com.akumar.rickandmorty.ui.theme.rnkSecondary
import com.akumar.rickandmorty.util.toThreeDigitString
import com.akumar.rickandmorty.util.with50PercentBlend

@Composable
fun RickAndMortyCard(
    rickAndMorty: RickAndMorty,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    val defaultDominantColor = Color.LightGray
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    Box(
        modifier = modifier
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(10 / 12f)
            .background(
                dominantColor.with50PercentBlend()
            )
            .clickable {
                navController.navigate(
                    "rickAndMorty_detail_screen/${rickAndMorty.id}"
                )
            }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(rickAndMorty.image)
                    .listener(onSuccess = { request, result ->
                        viewModel.calcDominantColor(result.drawable) { color ->
                            dominantColor = color
                        }
                    })
                    .build(),
                loading = {
                    Box(
                        contentAlignment = Center,
                        modifier = Modifier
                            .size(100.dp)
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                },
                contentDescription = rickAndMorty.name,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(rnkSecondary, Color.Black)
                        )
                    ),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = rickAndMorty.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                )
            }
        }
        Text(
            text = "#${rickAndMorty.id.toThreeDigitString()}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.None,
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 10.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(rnkSecondary, Color.Black)
                    )
                )
                .align(Alignment.TopEnd)
                .padding(4.dp)
        )
    }
}