package com.route.data.api.model.brands

import com.google.gson.annotations.SerializedName
import com.route.data.api.model.BrandDto

data class BrandsResponse(

    @field:SerializedName("document")
	val data: List<BrandDto?>? = null,

    @field:SerializedName("totalPages")
	val totalPages: Int? = null,

    @field:SerializedName("message")
	val message: String? = null
)