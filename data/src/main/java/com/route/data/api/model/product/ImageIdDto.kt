package com.route.data.api.model.product

import com.google.gson.annotations.SerializedName
import com.route.domain.models.ImageId

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