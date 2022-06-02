package com.rul8let.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class InfoLoad(
    @SerializedName("prev")
    val prevPage : String?,
    @SerializedName("next")
    val nextPage : String?
)