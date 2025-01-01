package com.route.domain.common

sealed class MyResult <out T> {

    data class Success<out T>(val data: T) : MyResult<T>()
    data class Failure(val exception: Throwable) : MyResult<Nothing>()
    data class ServerFail(val serverError: ServerError) : MyResult<Nothing>()

    object Loading : MyResult<Nothing>()



}