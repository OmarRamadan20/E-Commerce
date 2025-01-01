package com.route.data.api.model.wishlist

import com.route.domain.models.wishlist.WishListResponse

data class AddToWishListResponseDto(
    val message:String?=null
){
    fun toAddToWishListResponse(): WishListResponse {
        return WishListResponse(message)
    }
}
