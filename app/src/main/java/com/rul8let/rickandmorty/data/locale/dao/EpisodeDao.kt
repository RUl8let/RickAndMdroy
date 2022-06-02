package com.rul8let.rickandmorty.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rul8let.rickandmorty.data.model.EpisodeData

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodeList: List<EpisodeData>)

    @Query("SELECT * FROM episode WHERE id IN (:episodeId)")
    suspend fun getEpisode(episodeId : List<Int>) : List<EpisodeData>
}