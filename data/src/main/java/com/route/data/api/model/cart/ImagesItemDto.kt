package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.domain.models.ImageId
import com.route.domain.models.ImagesItem

data class ImagesItemDto(

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("image_id")
	val imageId: ImageId? = null
){
	fun toImagesItem() = ImagesItem(id,imageId)
}