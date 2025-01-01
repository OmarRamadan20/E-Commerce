package com.route.data.datasource

import com.route.data.api.WebServices
import com.route.data.contract.BrandsDataSource
import com.route.data.executeApi
import com.route.domain.models.Brand
import javax.inject.Inject

class BrandsDataSourceImpl @Inject constructor(
    val webServices: WebServices): BrandsDataSource {
    override suspend fun getAllBrands(): List<Brand?>? {
        val response = executeApi {
            webServices.getAllBrands()
        }.data?.map {
            it?.toBrand()
        }
        return response
    }
}