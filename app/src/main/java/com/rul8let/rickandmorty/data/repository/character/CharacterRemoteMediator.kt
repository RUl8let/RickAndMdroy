package com.rul8let.rickandmorty.data.repository.character

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rul8let.rickandmorty.data.locale.RickAndMortyDB
import com.rul8let.rickandmorty.data.model.CharacterData
import com.rul8let.rickandmorty.data.model.PageKey
import com.rul8let.rickandmorty.data.remote.CharacterApi
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CharacterRemoteMediator(
    private val networkApi: CharacterApi,
    private val localData: RickAndMortyDB
        ) : RemoteMediator<Int, CharacterData>() {



    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterData>
    ): MediatorResult {
        val page = when(loadType){
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: startPage
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)

                nextKey
            }
        }

         try {
            val response = networkApi.getAllCharacters(page)
             val nextPage : Int? = uriParserArgs(response.info.nextPage, argPage)
             val prevPage : Int? = uriParserArgs(response.info.prevPage, argPage)

            localData.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    localData.characterDao().clearAll()
                    localData.pageKayDao().clearAll()
                }

                val keyList = response.result.map {
                    PageKey(id = it.id, prevPage = prevPage, nextPage = nextPage)
                }
                localData.pageKayDao().insertAll(keyList)
                localData.characterDao().insertAll(response.result)
            }
             return MediatorResult.Success(endOfPaginationReached = response.result.isEmpty())
        } catch (e: IOException) {
             return MediatorResult.Error(e)
        } catch (e: HttpException) {
             return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterData>): PageKey? {
        return state.pages.lastOrNull{ it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                localData.pageKayDao().keyCharacterId(character.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterData>): PageKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                localData.pageKayDao().keyCharacterId(character.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterData>
    ): PageKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { character ->
                localData.pageKayDao().keyCharacterId(character)
            }
        }
    }

    private fun uriParserArgs(s : String?, uriArgs : String): Int?{
        if (!s.isNullOrEmpty())
            return Uri.parse(s).getQueryParameter(uriArgs)?.toInt()
        return null
    }

    companion object {
        const val startPage = 1
        const val argPage = "page"
        const val pageSize = 20
    }

}