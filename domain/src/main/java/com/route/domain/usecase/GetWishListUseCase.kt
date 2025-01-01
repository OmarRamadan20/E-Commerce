package com.route.domain.usecase

import android.util.Log
import com.route.domain.common.MyResult
import com.route.domain.contract.GetWishListRepository
import com.route.domain.models.wishlist.getWishlist.WishlistItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWishListUseCase @Inject constructor(private val repository: GetWishListRepository) {

    suspend fun invoke(token:String) :Flow<MyResult<List<WishlistItem?>?>> {
        return try {
            val response = repository.getWishList(token)
            response

        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            throw e
        }
    }
}