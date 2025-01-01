package com.route.data.datasource

import com.route.data.api.WebServices
import com.route.data.contract.ProductsOnlineDataSource
import com.route.data.executeApi
import com.route.domain.models.Product
import javax.inject.Inject

class ProductsOnlineDataSourceImpl @Inject constructor(val webServices: WebServices):ProductsOnlineDataSource {
    override suspend fun getProducts(category: String?): List<Product?>? {
        val response = executeApi { webServices.getProducts(category)}.data?.map {
            it?.toProduct()
        }
        return response
    }

}
