package com.rul8let.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pageKey")
data class PageKey(
    @PrimaryKey val id : Int,
    val prevPage : Int?,
    val nextPage : Int?
)
