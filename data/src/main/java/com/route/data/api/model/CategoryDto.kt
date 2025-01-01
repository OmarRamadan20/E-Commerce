package com.route.data.api.model

import com.google.gson.annotations.SerializedName
import com.route.data.api.model.product.Image
import com.route.domain.models.Category

data class CategoryDto(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
) {
	fun toCategory() :Category{
		return Category(
			image = image?.toImage(),
			name = name,
			id = id
		)
	}
}