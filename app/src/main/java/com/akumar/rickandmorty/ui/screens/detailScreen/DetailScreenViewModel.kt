package com.akumar.rickandmorty.ui.screens.detailScreen

import androidx.lifecycle.ViewModel
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.repository.RickAndMortyRepository
import com.akumar.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    suspend fun getRickAndMortyDetails(id: Int): Resource<RickAndMorty> {
        return repository.getRickAndMortyDetails(id)
    }
}