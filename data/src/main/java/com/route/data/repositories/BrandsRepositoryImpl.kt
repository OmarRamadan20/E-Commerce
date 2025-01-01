package com.route.data.repositories

import com.route.data.contract.BrandsDataSource
import com.route.data.toFlow
import com.route.domain.common.MyResult
import com.route.domain.contract.BrandsRepository
import com.route.domain.models.Brand
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BrandsRepositoryImpl @Inject constructor
    (val dataSource: BrandsDataSource):BrandsRepository{
    override suspend fun getAllBrands(): Flow<MyResult<List<Brand?>?>> {
        return toFlow {
            dataSource.getAllBrands()
        }
    }
}