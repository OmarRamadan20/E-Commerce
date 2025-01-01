package com.route.data.repositories

import android.util.Log
import com.route.data.contract.WishListDataSource
import com.route.domain.common.MyResult
import com.route.domain.contract.WishListRepository
import com.route.domain.models.wishlist.WishListRequest
import com.route.domain.models.wishlist.WishListResponse
import javax.inject.Inject

class WishListRepositoryImpl @Inject constructor(private val dataSource: WishListDataSource)
    :WishListRepository {

    override suspend fun updateWishlist(wishListRequest: WishListRequest, token:String)
    : MyResult<WishListResponse> {
        return try {
            val response= dataSource.updateWishlist(wishListRequest,token).toAddToWishListResponse()
            Log.e("AddToWishListRepositoryImpl","$response")
            MyResult.Success(response)
        }
        catch (e:Exception){
            MyResult.Failure(e)
        }

    }
}