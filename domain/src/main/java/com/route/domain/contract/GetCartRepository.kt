package com.route.domain.contract

import com.route.domain.models.Product

interface GetCartRepository {

    suspend fun getCart(token:String):List<Product>
}