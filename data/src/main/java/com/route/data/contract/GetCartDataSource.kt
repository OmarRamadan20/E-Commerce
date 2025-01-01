package com.route.data.contract

import com.route.domain.models.Product

interface GetCartDataSource {

    suspend fun getCart(token:String): List<Product>
}