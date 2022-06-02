package com.rul8let.rickandmorty.data.remote

import com.rul8let.rickandmorty.data.model.EpisodeData
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("episode/{list_id}")
    suspend fun getEpisodes(
        @Path("list_id") list_id : List<Int>
    ) : List<EpisodeData>
}