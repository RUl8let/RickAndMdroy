package com.rul8let.rickandmorty.data.repository.episode

import android.net.Uri
import com.rul8let.rickandmorty.data.Response
import com.rul8let.rickandmorty.data.locale.dao.EpisodeDao
import com.rul8let.rickandmorty.data.model.EpisodeData
import com.rul8let.rickandmorty.data.remote.EpisodeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeApi: EpisodeApi,
    private val episodeBase: EpisodeDao
): EpisodeRepository {

    override suspend fun getEpisode(episodeList : List<String>) : Flow<Response<List<EpisodeData>>>
    = flow{
        emit(Response.Loading)
        val idList = episodeList.map {
            Uri.parse(it).lastPathSegment?.toInt()?:0
        }

        val localData = episodeBase.getEpisode(idList)

        if(localData.size==idList.size){
            emit(Response.Success(localData))
        } else
            try {
                val response = episodeApi.getEpisodes(idList)
                episodeBase.insertAll(response)
                emit(Response.Success(response))
            } catch (e: IOException) {
                emit(Response.Error(e))
            } catch (e: HttpException) {
                emit(Response.Error(e))
            }

    }
}