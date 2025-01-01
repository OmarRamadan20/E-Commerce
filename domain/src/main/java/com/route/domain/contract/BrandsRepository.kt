package com.route.domain.contract

import com.route.domain.common.MyResult
import com.route.domain.models.Brand
import kotlinx.coroutines.flow.Flow

interface BrandsRepository {
    suspend fun getAllBrands(): Flow<MyResult<List<Brand?>?>>
}