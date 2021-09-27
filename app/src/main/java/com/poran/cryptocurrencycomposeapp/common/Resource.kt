package com.poran.cryptocurrencycomposeapp.common

/*sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(error: String?, data: T? = null): Resource<T>(data,error)
    class Loading(data: T? = null): Resource<T>(data)
}*/

sealed class Resource<T>(private val data: T? = null, private val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>( data: T? = null) : Resource<T>(data)
}
