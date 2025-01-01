package com.route.domain.usecase

import android.util.Log
import com.route.domain.common.MyResult
import com.route.domain.contract.GetCartRepository
import com.route.domain.models.Product
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val getCartRepository: GetCartRepository) {
    suspend fun invoke(token:String):MyResult<List<Product?>?>{
        return try {
            val response = getCartRepository.getCart(token)
            Log.e("use case response", "$response")
            MyResult.Success(response)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }
}