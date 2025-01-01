package com.route.domain.usecase

import com.route.domain.common.MyResult
import com.route.domain.contract.CategoriesRepository
import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoriesRepository
) {
    suspend fun invoke (): Flow<MyResult<List<Category?>?>> {
        return categoryRepository.getAllCategories()
    }
}