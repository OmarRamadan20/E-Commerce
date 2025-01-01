package com.route.domain.usecase

import com.route.domain.common.MyResult
import com.route.domain.contract.ProductsRepository
import com.route.domain.models.Category
import com.route.domain.models.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(private val productsRepository: ProductsRepository) {
    suspend fun invoke (category:String?): Flow<MyResult<List<Product?>?>> {
        return productsRepository.getProducts(category)
    }
}