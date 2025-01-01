package com.route.domain.usecase

import com.route.domain.common.MyResult
import com.route.domain.contract.WishListRepository
import com.route.domain.models.wishlist.WishListRequest
import com.route.domain.models.wishlist.WishListResponse
import javax.inject.Inject

class UpdateWishlistUseCase @Inject constructor(private val repository: WishListRepository){

    suspend fun invoke(wishListRequest: WishListRequest
                       , token:String):MyResult<WishListResponse>{
        return repository.updateWishlist(wishListRequest,token)
    }


}