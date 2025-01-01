package com.route.domain.contract

import com.route.domain.common.MyResult
import com.route.domain.models.wishlist.getWishlist.WishlistItem
import kotlinx.coroutines.flow.Flow

interface GetWishListRepository {
    suspend fun getWishList(token:String): Flow<MyResult<List<WishlistItem?>?>>
}