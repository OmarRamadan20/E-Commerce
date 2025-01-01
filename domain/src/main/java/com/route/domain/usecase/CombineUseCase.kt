package com.route.domain.usecase

import android.util.Log
import com.route.domain.common.MyResult
import com.route.domain.contract.BrandsRepository
import com.route.domain.contract.CategoriesRepository
import com.route.domain.contract.ProductsRepository
import com.route.domain.models.CombineData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class CombineUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val productsRepository: ProductsRepository,
    private val brandsRepository: BrandsRepository
) {
    suspend fun invoke(category:String?): Flow<MyResult<CombineData>> {
        val categories = categoriesRepository.getAllCategories()
        val products = productsRepository.getProducts(category)
        val brands=brandsRepository.getAllBrands()
        return combine(categories, products,brands) { categories, products,brands ->
            when {
                categories is MyResult.Failure -> {
                    Log.e("CombineUseCase", "invoke: Failure categories")

                    categories
                }
                products is MyResult.Failure -> {
                    Log.e("CombineUseCase", "invoke: Failure products")
                    products

                }
                brands is MyResult.Failure -> {
                    Log.e("CombineUseCase", "invoke: Failure products")
                    brands

                }

                categories is MyResult.ServerFail -> categories
                products is MyResult.ServerFail -> products
                brands is MyResult.ServerFail -> brands

                categories is MyResult.Success &&
                        products is MyResult.Success && brands is MyResult.Success -> {
                    Log.e("CombineUseCase", "invoke: Success")
                    MyResult.Success(CombineData(categories.data, products.data,brands.data))
                }
                else -> MyResult.Loading
            }
        }
    }
}