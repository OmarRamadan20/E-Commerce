package com.route.data.contract

import com.route.data.api.model.wishlist.AddToWishListResponseDto
import com.route.domain.models.wishlist.WishListRequest

interface WishListDataSource {
    suspend fun updateWishlist (wishListRequest: WishListRequest
                                     , token: String):AddToWishListResponseDto
}