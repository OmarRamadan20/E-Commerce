package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductId(

    val sold: Int? = null,

    val images: List<ImagesItem?>? = null,

    val description: String? = null,

    val title: String? = null,

    val createdAt: String? = null,

    val rateCount: Int? = null,

    val price: Int? = null,

    val v: Int? = null,

    val id: String? = null,

    val stock: Int? = null,

    val category: UserCategory? = null,

    val subcategory: String? = null,

    val priceAfterDiscount: Int? = null,

    val brand: Brand? = null,

    val slug: String? = null,

    val imgCover: ImgCover? = null,

    val updatedAt: String? = null
):Parcelable{
    fun toProduct(): Product {
        return Product(
            sold,
            images,
            description,
            title,
            createdAt,
            rateCount,
            price,
            v,
            id,
            stock,
            category?.toCategory(),
            subcategory,
            priceAfterDiscount,
            brand,
            slug,
            imgCover,
            updatedAt
        )
    }
}