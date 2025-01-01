package com.route.domain.contract

import com.route.domain.common.MyResult
import com.route.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(  categoryId:String?
    ): Flow<MyResult<List<Product?>?>>
}
//enum class SortBy(val value:String){
//    PRICE_Desc("-price"),
//    PRICE_Asc("price"),
//    MOST_SELLING("-sold")
//}

//Parametares
//        categoryId : String? = null,
//        sortBy:SortBy?=null,
//        price:String?=null,
//        brand:String?=null,
