package com.route.domain.common

sealed class CustomException(val msg: String?, val errorBody: String?) : Exception(msg) {

    class ApiException(val errorCode: Int?, errorMessage: String?, errorBody: String?) :
        CustomException(errorMessage, errorBody)

    class NetworkException(errorMessage: String?) :
        CustomException(errorMessage, null)

    class UnknownException(errorMessage: String?) :
        CustomException(errorMessage, null)
}