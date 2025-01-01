package com.route.data.repositories

import com.route.data.contract.GetCartDataSource
import com.route.domain.contract.GetCartRepository
import com.route.domain.models.Product
import javax.inject.Inject

class GetCartRepositoryImpl @Inject constructor(private val dataSource: GetCartDataSource)
    :GetCartRepository {
    override suspend fun getCart(token: String): List<Product> {
                return dataSource.getCart(token)
    }

}