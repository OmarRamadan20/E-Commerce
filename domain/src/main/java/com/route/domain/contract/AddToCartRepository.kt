package com.route.domain.contract

import com.route.domain.common.MyResult
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.AddToCartResponse

interface AddToCartRepository {
    suspend fun addToCart(addtocartRequest: AddToCartRequest, token:String):MyResult<AddToCartResponse>
}