package com.rul8let.rickandmorty.data.repository.character

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rul8let.rickandmorty.data.locale.RickAndMortyDB
import com.rul8let.rickandmorty.data.model.CharacterData
import com.rul8let.rickandmorty.data.remote.CharacterApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi,
    private val database: RickAndMortyDB
): CharacterRepository {

    @ExperimentalPagingApi
    override fun getCharacterStream(): Flow<PagingData<CharacterData>> {
        return Pager(
            config = PagingConfig(CharacterRemoteMediator.pageSize),
            remoteMediator = CharacterRemoteMediator(characterApi,database),
            pagingSourceFactory = { database.characterDao().character() }
        ).flow
    }
}