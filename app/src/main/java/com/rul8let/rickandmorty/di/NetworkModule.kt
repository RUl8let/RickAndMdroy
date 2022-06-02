package com.rul8let.rickandmorty.di

import com.rul8let.rickandmorty.data.remote.CharacterApi
import com.rul8let.rickandmorty.data.remote.EpisodeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val baseURL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(retrofit: Retrofit) : CharacterApi =
        retrofit.create(CharacterApi::class.java)

    @Provides
    @Singleton
    fun provideEpisodeApi(retrofit: Retrofit) : EpisodeApi =
        retrofit.create(EpisodeApi::class.java)
}