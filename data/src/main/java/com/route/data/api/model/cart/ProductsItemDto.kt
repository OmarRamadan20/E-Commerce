package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.domain.models.ProductsItem

data class ProductsItemDto(

    @field:SerializedName("quantity")
    val quantity: Int? = null,

    @field:SerializedName("product_id")
    val productIdDto: ProductIdDto? = null,

    @field:SerializedName("_id")
    val id: String? = null,
){
    fun toProductsItem(): ProductsItem {
        return ProductsItem(
            quantity = quantity,
            productId = productIdDto?.toProductId(),
            id = id
        )
    }
}