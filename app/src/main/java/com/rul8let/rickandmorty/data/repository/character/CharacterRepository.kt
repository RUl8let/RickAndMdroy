package com.rul8let.rickandmorty.data.repository.character

import androidx.paging.PagingData
import com.rul8let.rickandmorty.data.model.CharacterData
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacterStream(): Flow<PagingData<CharacterData>>
}