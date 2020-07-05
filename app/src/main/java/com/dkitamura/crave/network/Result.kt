package com.dkitamura.crave.network

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    object InProgress: Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()
}