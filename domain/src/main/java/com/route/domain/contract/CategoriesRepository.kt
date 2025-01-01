package com.route.domain.contract

import com.route.domain.common.MyResult
import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories(): Flow<MyResult<List<Category?>?>>
}