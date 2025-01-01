package com.route.data.repositories

import com.route.data.api.WebServices
import com.route.domain.common.MyResult
import com.route.domain.contract.AddToCartRepository
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.AddToCartResponse

import javax.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(val webServices : WebServices):AddToCartRepository {

    override suspend fun addToCart(
        addToCartRequest: AddToCartRequest,
        token: String
    ): MyResult<AddToCartResponse> {
        return try{
            val response = webServices.addToCart(addToCartRequest,  token).toCartResponse()
            MyResult.Success(response)
        }catch (e:Exception){
            MyResult.Failure(e)
        }
    }

}