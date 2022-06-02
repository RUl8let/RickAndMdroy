package com.rul8let.rickandmorty.data.repository.episode

import com.rul8let.rickandmorty.data.Response
import com.rul8let.rickandmorty.data.model.EpisodeData
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    suspend fun getEpisode(episodeList : List<String>) : Flow<Response<List<EpisodeData>>>
}