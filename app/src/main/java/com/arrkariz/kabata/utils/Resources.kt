package com.arrkariz.kabata.utils

sealed class Resources<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resources<T>(data)
    class Empty<T>(message: String): Resources<T>(message = message)
    class Error<T>(message: String, data: T? = null): Resources<T>(data, message)
    class Loading<T>(data: T? = null): Resources<T>(data)
}