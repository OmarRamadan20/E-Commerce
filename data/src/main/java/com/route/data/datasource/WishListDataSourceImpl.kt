package com.route.data.datasource

import com.route.data.api.WebServices
import com.route.data.api.model.wishlist.AddToWishListResponseDto
import com.route.data.contract.WishListDataSource
import com.route.data.executeApi
import com.route.domain.models.wishlist.WishListRequest
import javax.inject.Inject

class WishListDataSourceImpl @Inject constructor
    (private val webServices: WebServices): WishListDataSource {
    override suspend fun updateWishlist(
        wishListRequest: WishListRequest,
        token: String
    ): AddToWishListResponseDto {
        return executeApi {
            webServices.updateWishlist(wishListRequest,token)
        }
    }
}