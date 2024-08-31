package com.akumar.rickandmorty.di

import com.akumar.rickandmorty.data.remote.RickAndMortyApi
import com.akumar.rickandmorty.repository.RickAndMortyRepository
import com.akumar.rickandmorty.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRickAndMortyRepository(
        rickAndMortyApi: RickAndMortyApi
    ) = RickAndMortyRepository(rickAndMortyApi)

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(RickAndMortyApi::class.java)
    }
}