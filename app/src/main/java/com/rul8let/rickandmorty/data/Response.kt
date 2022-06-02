package com.rul8let.rickandmorty.data

sealed class Response<out R> {
    data class Success<out T : Any>(val data: T) : Response<T>()
    object Loading : Response<Nothing>()
    data class Error(val error: Exception) : Response<Nothing>()
}