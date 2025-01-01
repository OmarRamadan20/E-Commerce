package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.data.api.model.product.ImagesItemDto
import com.route.data.api.model.product.ImgCover
import com.route.domain.models.ProductId

data class ProductIdDto(

    @field:SerializedName("sold")
    val sold: Int? = null,

    @field:SerializedName("images")
    val images: List<ImagesItemDto?>? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("rateCount")
    val rateCount: Int? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("stock")
    val stock: Int? = null,

    @field:SerializedName("category")
    val categoryDto: CategoryDto? = null,

    @field:SerializedName("subcategory")
    val subcategory: String? = null,

    @field:SerializedName("priceAfterDiscount")
    val priceAfterDiscount: Int? = null,

    @field:SerializedName("brand")
    val brandDto: BrandDto? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("imgCover")
    val imgCover: ImgCover? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
){
    fun toProductId():ProductId{
        return ProductId(
            sold,
            images?.map {
                it?.toImage()
            },
            description,
            title,
            createdAt,
            rateCount,
            price,
            v,
            id,
            stock,
            categoryDto?.toCategory(),
            subcategory,
            priceAfterDiscount,
            brandDto?.toBrand(),
            slug,
            imgCover?.toImgCover(),
            updatedAt
        )
    }
}