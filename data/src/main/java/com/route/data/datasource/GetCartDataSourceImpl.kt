package com.route.data.datasource

import com.route.data.api.WebServices
import com.route.data.contract.GetCartDataSource
import com.route.domain.models.Product
import com.route.domain.models.ProductId
import javax.inject.Inject

class GetCartDataSourceImpl @Inject constructor(val webServices: WebServices): GetCartDataSource {
    override suspend fun getCart(token: String): List<Product> {
        val response= webServices.getCartItems(token).cart!!.map {
            it!!.products?.map {
                it?.productId
            }
        }
        return mapToWishListItems(response.filterNotNull())
    }

    private fun mapToWishListItems(nestedProductIds: List<List<ProductId?>>): List<Product> {
        return nestedProductIds
            .flatten() // Flatten the nested list
            .filterNotNull() // Remove nulls
            .map { productId ->
                Product(
                    sold = productId.sold,
                    description = productId.description,
                    title = productId.title,
                    createdAt = productId.createdAt,
                    price = productId.price,
                    v = productId.v,
                    id = productId.id,
                    category = productId.category?.toCategory(),
                    brand = productId.brand,
                    slug = productId.slug,
                    updatedAt = productId.updatedAt
                )
            }
    }
}