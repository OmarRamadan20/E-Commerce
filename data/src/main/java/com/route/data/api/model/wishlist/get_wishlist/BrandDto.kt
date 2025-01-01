package com.route.data.api.model.wishlist.get_wishlist

import com.google.gson.annotations.SerializedName
import com.route.domain.models.wishlist.getWishlist.Brand

data class BrandDto(

	@field:SerializedName("image")
	val image: ImageDto? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
){
	fun toBrand():Brand{
		return Brand(
			image?.toImage(),
			name,
			id
		)
	}

}