package com.akumar.rickandmorty.ui.screens.detailScreen.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.ui.screens.detailScreen.DetailScreenViewModel
import com.akumar.rickandmorty.util.Resource

@Composable
fun DetailStateWrapper(
    id: Int,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val rickAndMortyDetail by produceState<Resource<RickAndMorty>>(initialValue = Resource.Loading()) {
        value = detailScreenViewModel.getRickAndMortyDetails(id = id)
    }


    when (rickAndMortyDetail) {
        is Resource.Success -> rickAndMortyDetail.data?.let { DetailSection(it) }
        is Resource.Error -> Text(
            text = rickAndMortyDetail.message.orEmpty(),
            color = Color.Red,
            modifier = modifier
        )


        is Resource.Loading -> CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = loadingModifier
        )
    }
}