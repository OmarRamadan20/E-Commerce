package com.route.data.api.model.product

import com.google.gson.annotations.SerializedName
import com.route.data.api.model.product.ImageIdDto
import com.route.domain.models.ImagesItem

data class ImagesItemDto(

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("image_id")
	val imageId: ImageIdDto? = null
){
	fun toImage():ImagesItem{
		return ImagesItem(id,imageId?.toImageId())
	}

}