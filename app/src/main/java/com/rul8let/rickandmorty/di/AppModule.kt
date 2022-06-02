package com.rul8let.rickandmorty.di

import com.rul8let.rickandmorty.data.repository.character.CharacterRepository
import com.rul8let.rickandmorty.data.repository.character.CharacterRepositoryImpl
import com.rul8let.rickandmorty.data.repository.episode.EpisodeRepository
import com.rul8let.rickandmorty.data.repository.episode.EpisodeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindCharacter(characterRepository: CharacterRepositoryImpl) : CharacterRepository

    @Binds
    abstract fun bindEpisode(episodeRepository: EpisodeRepositoryImpl) : EpisodeRepository

}