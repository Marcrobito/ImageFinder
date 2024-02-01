package io.hannah.imagefinder.model

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val error: Exception) : NetworkResponse<Nothing>()
}