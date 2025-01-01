package com.route.data.contract

import com.route.data.api.model.cart.AddToCartResponseDto
import com.route.domain.models.AddToCartRequest

interface AddToCartDataSource {
    suspend fun addToCart(addToCartRequest: AddToCartRequest, token:String): AddToCartResponseDto
}

