package com.route.data.api.model

import com.google.gson.annotations.SerializedName
import com.route.data.api.model.product.Image
import com.route.domain.models.Brand

data class BrandDto(

    @field:SerializedName("image")
	val image: Image? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("_id")
	val id: String? = null,

    @field:SerializedName("slug")
	val slug: String? = null
)
{
	fun toBrand(): Brand {
		return Brand(
			image = image?.toImage(),
			name = name,
			id = id)
	}
}