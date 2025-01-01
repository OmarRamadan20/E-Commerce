package com.route.data.datasource

import com.route.data.api.WebServices
import com.route.data.api.model.cart.AddToCartResponseDto
import com.route.data.contract.AddToCartDataSource
import com.route.data.executeApi
import com.route.domain.models.AddToCartRequest
import javax.inject.Inject

class AddToCartDataSourceImpl @Inject constructor(private val webServices: WebServices)
    : AddToCartDataSource {
    override suspend fun addToCart(addToCartRequest: AddToCartRequest,token:String): AddToCartResponseDto {
        return executeApi { webServices.addToCart(addToCartRequest , token) }
    }
}