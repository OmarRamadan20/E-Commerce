package com.route.data.repositories

import android.util.Log
import com.route.data.contract.GetWishListDataSource
import com.route.data.toFlow
import com.route.domain.common.MyResult
import com.route.domain.contract.GetWishListRepository
import com.route.domain.models.wishlist.getWishlist.WishlistItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWishListRepositoryImpl @Inject constructor(val dataSource: GetWishListDataSource):GetWishListRepository {
    override suspend fun getWishList(token: String): Flow<MyResult<List<WishlistItem?>?>> {
        val response =  toFlow {
            dataSource.getWishList(token)
        }
        Log.e("GetWishList Repo","$response")
        return response
    }

}