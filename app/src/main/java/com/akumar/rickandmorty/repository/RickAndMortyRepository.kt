package com.akumar.rickandmorty.repository

import com.akumar.rickandmorty.data.remote.RickAndMortyApi
import com.akumar.rickandmorty.data.remote.responses.RickAndMorty
import com.akumar.rickandmorty.data.remote.responses.RickAndMortyList
import com.akumar.rickandmorty.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RickAndMortyRepository @Inject constructor(private val rickAndMortyApi: RickAndMortyApi) {
    suspend fun getRickAndMortyList(page: Int): Resource<RickAndMortyList> {
        val response = try {
            rickAndMortyApi.getRickAndMortyList(page)
        } catch (e: Exception) {
            return Resource.Error("Something went wrong")
        }
        return Resource.Success(response)
    }

    suspend fun getRickAndMortyDetails(pokemonId: Int): Resource<RickAndMorty> {
        val response = try {
            rickAndMortyApi.getRickAndMortyDetails(pokemonId)
        } catch (e: Exception) {
            return Resource.Error("Something went wrong")
        }
        return Resource.Success(response)
    }
}