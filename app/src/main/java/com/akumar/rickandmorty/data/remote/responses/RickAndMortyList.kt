package com.akumar.rickandmorty.data.remote.responses


import com.google.gson.annotations.SerializedName

data class RickAndMortyList(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<RickAndMorty>
)