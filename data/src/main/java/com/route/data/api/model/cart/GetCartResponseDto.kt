package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName

data class GetCartResponseDto(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("cart")
    val cart: List<CartDto?>? = null
){

}