package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName

data class ImageId(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)