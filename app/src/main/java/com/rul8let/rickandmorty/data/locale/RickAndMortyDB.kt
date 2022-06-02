package com.rul8let.rickandmorty.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rul8let.rickandmorty.data.locale.dao.CharacterDao
import com.rul8let.rickandmorty.data.locale.dao.CharacterPageKeyDao
import com.rul8let.rickandmorty.data.locale.dao.EpisodeDao
import com.rul8let.rickandmorty.data.model.CharacterData
import com.rul8let.rickandmorty.data.model.EpisodeData
import com.rul8let.rickandmorty.data.model.PageKey

@Database(entities = [CharacterData::class, PageKey::class, EpisodeData::class], version = 1)
@TypeConverters(ConverterStringList::class)
abstract class RickAndMortyDB : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun pageKayDao() : CharacterPageKeyDao
    abstract fun episodeDao() : EpisodeDao
}