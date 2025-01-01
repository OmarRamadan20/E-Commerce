package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.domain.models.Cart

data class CartDto(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("total_price")
    val totalPrice: Any? = null,

    @field:SerializedName("user_id")
    val userId: String? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("products")
    val products: List<ProductsItemDto?>? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
){
    fun toCart():Cart{
        return Cart(
            createdAt,
            totalPrice,
            userId,
            v,
            id,
            products?.map {
                it?.toProductsItem()
            },
            updatedAt
        )
    }
}