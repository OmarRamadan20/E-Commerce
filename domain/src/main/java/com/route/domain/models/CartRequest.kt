package com.route.domain.models

data class CartRequest(
    val productId: String?=null,
    val quantity: Int?=null,
)