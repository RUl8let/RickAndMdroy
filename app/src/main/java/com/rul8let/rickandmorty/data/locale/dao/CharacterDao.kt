package com.rul8let.rickandmorty.data.locale.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rul8let.rickandmorty.data.model.CharacterData

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(character: List<CharacterData>)

    @Query("SELECT * FROM character")
    fun character() : PagingSource<Int,CharacterData>

    @Query("DELETE FROM character")
    suspend fun clearAll()
}