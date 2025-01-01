package com.route.data.repositories

import android.util.Log
import com.route.data.contract.ProductsOnlineDataSource
import com.route.data.toFlow
import com.route.domain.common.MyResult
import com.route.domain.contract.ProductsRepository
import com.route.domain.models.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productsOnlineDataSource: ProductsOnlineDataSource) :ProductsRepository {
    override suspend fun getProducts(  category:String?
    ): Flow<MyResult<List<Product?>?>> {
        return toFlow {
            productsOnlineDataSource.getProducts(category)
        }
    }

}