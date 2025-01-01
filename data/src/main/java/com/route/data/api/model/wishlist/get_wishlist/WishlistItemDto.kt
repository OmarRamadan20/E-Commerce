package com.route.data.api.model.wishlist.get_wishlist

import com.google.gson.annotations.SerializedName
import com.route.domain.models.wishlist.getWishlist.WishlistItem

data class WishlistItemDto(

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

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("category")
	val category: CategoryDto? = null,

	@field:SerializedName("subcategory")
	val subcategory: String? = null,

	@field:SerializedName("priceAfterDiscount")
	val priceAfterDiscount: Int? = null,

	@field:SerializedName("brand")
	val brand: BrandDto? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("imgCover")
	val imgCover: ImgCoverDto? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
){
	fun toWishList(): WishlistItem {
		return WishlistItem(
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
			category?.toCategory(),
			subcategory,
			priceAfterDiscount,
			brand?.toBrand(),
			slug,
			imgCover?.toImageCover(),
			updatedAt
		)
	}
}