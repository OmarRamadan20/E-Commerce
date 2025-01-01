package com.route.data.api.model.wishlist.get_wishlist

import com.google.gson.annotations.SerializedName
import com.route.domain.models.wishlist.getWishlist.ImageId

data class ImageIdDto(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
){
	fun toImageId():ImageId{
		return ImageId(path,id)

	}
}