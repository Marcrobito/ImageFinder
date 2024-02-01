package io.hannah.imagefinder.model

sealed class NetworkRequest<out T> {
    data class Success<out T>(val data: T) : NetworkRequest<T>()
    data class Error(val error: RequestError) : NetworkRequest<Nothing>()
}

data class RequestError(
    val message: String,
    val errorCode: Int
)