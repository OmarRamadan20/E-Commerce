package com.route.data.api.model.wishlist.get_wishlist

import com.google.gson.annotations.SerializedName
import com.route.domain.models.wishlist.getWishlist.Image

data class ImageDto(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
){
	fun toImage() : Image{
		return Image(
			path = path,
			id = id
		)
	}
}