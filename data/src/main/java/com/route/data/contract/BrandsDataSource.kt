package com.route.data.contract

import com.route.domain.models.Brand

interface BrandsDataSource {

    suspend fun getAllBrands():List<Brand?>?
}