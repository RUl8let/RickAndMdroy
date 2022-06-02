package com.rul8let.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.rul8let.rickandmorty.data.locale.RickAndMortyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun characterDataBaseBuild(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RickAndMortyDB::class.java,
        "RickAndMorty"
    ).build()

    @Provides
    fun provideCharacterData(data : RickAndMortyDB) = data.characterDao()

    @Provides
    fun provideCharacterPageKeyData(data : RickAndMortyDB) = data.pageKayDao()

    @Provides
    fun provideEpisodeData(data : RickAndMortyDB) = data.episodeDao()
}