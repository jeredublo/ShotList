package com.example.shotlist.base_mvi


// sealed classes are when you don't want to hardcode values into them
sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    object Loading : DataResult<Nothing>()
    data class Error(val errorMessage: String) : DataResult<Nothing>()
}