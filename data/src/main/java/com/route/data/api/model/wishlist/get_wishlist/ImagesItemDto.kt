package com.route.data.api.model.wishlist.get_wishlist

import com.google.gson.annotations.SerializedName
import com.route.domain.models.wishlist.getWishlist.ImagesItem

data class ImagesItemDto(

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("image_id")
	val imageId: ImageIdDto? = null
){
	fun toImage(): ImagesItem {
		return ImagesItem(
			id, imageId?.toImageId()
		)
	}
}