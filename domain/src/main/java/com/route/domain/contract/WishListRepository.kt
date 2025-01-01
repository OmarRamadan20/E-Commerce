package com.route.domain.contract

import com.route.domain.common.MyResult
import com.route.domain.models.wishlist.WishListRequest
import com.route.domain.models.wishlist.WishListResponse

interface WishListRepository {
    suspend fun updateWishlist(wishListRequest: WishListRequest, token:String):MyResult<WishListResponse>

}