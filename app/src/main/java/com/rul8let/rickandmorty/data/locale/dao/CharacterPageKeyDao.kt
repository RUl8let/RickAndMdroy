package com.rul8let.rickandmorty.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rul8let.rickandmorty.data.model.PageKey

@Dao
interface CharacterPageKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(key : List<PageKey>)

    @Query("SELECT * FROM pageKey WHERE id = :characterId")
    suspend fun keyCharacterId(characterId: Int): PageKey?

    @Query("DELETE FROM pageKey")
    suspend fun clearAll()

}