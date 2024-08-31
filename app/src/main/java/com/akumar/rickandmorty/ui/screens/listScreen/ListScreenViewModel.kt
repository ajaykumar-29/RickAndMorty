package com.akumar.rickandmorty.ui.screens.listScreen

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.repository.RickAndMortyRepository
import com.akumar.rickandmorty.util.Constants.PAGE_SIZE
import com.akumar.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    private var currentPage = 1

    var rickAndMortyList = MutableStateFlow<List<RickAndMorty>>(listOf())
    var loadError = MutableStateFlow("")
    var isLoading = MutableStateFlow(false)
    var endReached = MutableStateFlow(false)

    init {
        loadRickAndMortyPaginated()
    }

    fun loadRickAndMortyPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            loadError.value = ""
            val result = repository.getRickAndMortyList(currentPage)
            when (result) {
                is Resource.Success -> {
                    endReached.value = currentPage * PAGE_SIZE >= result.data!!.info.count
                    currentPage++
                    loadError.value = ""
                    isLoading.value = false
                    rickAndMortyList.value += result.data.results
                }

                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }

                is Resource.Loading -> {

                }
            }
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}