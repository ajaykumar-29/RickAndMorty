package com.akumar.rickandmorty.ui.screens.listScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.akumar.rickandmorty.R
import com.akumar.rickandmorty.ui.screens.listScreen.components.RickAndMortyList


@Composable
fun RickAndMortyListScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val innerPaddingValue by remember {
        mutableStateOf(
            if (screenWidth < 600.dp) innerPadding else PaddingValues(
                0.dp
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(MaterialTheme.colorScheme.background)
    )
    Column(
        modifier = Modifier
            .padding(innerPaddingValue)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty_logo),
            contentDescription = "RickAndMorty",
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .background(MaterialTheme.colorScheme.background)
        )
//        HorizontalDivider(
//            modifier = Modifier.shadow(10.dp),
//            thickness = 2.dp,
//            color = Color(0xFFBFDE42)
//        )
        RickAndMortyList(navController = navController)
    }
}