package com.route.data.contract

import com.route.domain.models.wishlist.getWishlist.WishlistItem

interface GetWishListDataSource {

    suspend fun getWishList(token:String): List<WishlistItem?>?
}