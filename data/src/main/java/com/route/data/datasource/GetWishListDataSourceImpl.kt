package com.route.data.datasource

import android.util.Log
import com.route.data.api.WebServices
import com.route.data.contract.GetWishListDataSource
import com.route.data.executeApi
import com.route.domain.models.wishlist.getWishlist.WishlistItem
import javax.inject.Inject

class GetWishListDataSourceImpl @Inject constructor(val webServices: WebServices) :GetWishListDataSource {
    override suspend fun getWishList(token: String): List<WishlistItem?>? {

        val response = executeApi {
            webServices.getWishList(token)
        }.wishlist?.map {
            it?.toWishList()
        }
        Log.e("GetWishlist DataSource", "$response")
        return response
    }
}