package com.example.preapp.data.model

sealed class HttpResponceState<out T> {
    class Success<out T>(val value: T) : HttpResponceState<T>()
    class Failure(val message: String) : HttpResponceState<Nothing>()
    object Loading : HttpResponceState<Nothing>()
}