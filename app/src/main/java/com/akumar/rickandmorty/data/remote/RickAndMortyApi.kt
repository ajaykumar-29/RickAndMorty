package com.akumar.rickandmorty.data.remote

import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.data.remote.responses.RickAndMortyList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getRickAndMortyList(@Query("page") page: Int): RickAndMortyList

    @GET("character/{id}")
    suspend fun getRickAndMortyDetails(@Path("id") id: Int): RickAndMorty
}