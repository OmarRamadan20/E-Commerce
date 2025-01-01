package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.domain.models.AddToCartResponse

data class AddToCartResponseDto(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("cart")
    val cartDto: CartDto? = null
){
    fun toCartResponse() : AddToCartResponse{
        return AddToCartResponse(
            message = message,
            cart = cartDto?.toCart()
        )
    }
}