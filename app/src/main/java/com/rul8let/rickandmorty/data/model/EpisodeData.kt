package com.rul8let.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
data class EpisodeData(
    @PrimaryKey val id : Int,
    val name : String,
    val air_date : String,
    val episode : String
    )
