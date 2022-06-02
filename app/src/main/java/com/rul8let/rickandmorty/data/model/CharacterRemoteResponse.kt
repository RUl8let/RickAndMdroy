package com.rul8let.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class CharacterRemoteResponse (
    @SerializedName("info")
    val info : InfoLoad,
    @SerializedName("results")
    val result: List<CharacterData>
    )

