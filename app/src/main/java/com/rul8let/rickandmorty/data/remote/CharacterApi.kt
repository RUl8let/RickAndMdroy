package com.rul8let.rickandmorty.data.remote

import com.rul8let.rickandmorty.data.model.CharacterRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int? = null
    ): CharacterRemoteResponse

}