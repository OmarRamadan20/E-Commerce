package com.route.data.api.model.product

import com.google.gson.annotations.SerializedName
import com.route.domain.models.ImgCover

data class ImgCover(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
){
	fun toImgCover():ImgCover{
		return ImgCover(path,id)
	}
}