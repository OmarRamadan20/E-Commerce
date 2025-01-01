package com.route.data.contract

import com.route.domain.models.Product

interface ProductsOnlineDataSource {
    suspend fun getProducts(
        category:String?
    ): List<Product?>?
}