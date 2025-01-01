package com.route.data.api.model.wishlist.get_wishlist

import com.google.gson.annotations.SerializedName

data class WishListResponseDto(

	@field:SerializedName("wishlist")
	val wishlist: List<WishlistItemDto?>? = null
)