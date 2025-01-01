package com.route.domain.common

data class ServerError(val msg: String?=null, val error: String?=null) : Throwable()
