package com.route.domain.usecase

import com.route.domain.common.MyResult
import com.route.domain.contract.AddToCartRepository
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.AddToCartResponse
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val addToCartRepository: AddToCartRepository) {

    suspend fun invoke(addToCartRequest: AddToCartRequest, token: String): MyResult<AddToCartResponse> {
            return addToCartRepository.addToCart(addToCartRequest, token)

    }
}