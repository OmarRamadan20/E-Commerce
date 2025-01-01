package com.route.data.api.model.product

import com.google.gson.annotations.SerializedName
import com.route.domain.models.Image

data class Image(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
){
	fun toImage():Image{
		return Image(
			path=path,
			id=id
		)
	}
}