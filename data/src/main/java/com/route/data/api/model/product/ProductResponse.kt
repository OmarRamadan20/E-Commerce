package com.route.data.api.model.product

import com.google.gson.annotations.SerializedName
import com.route.data.api.model.Metadata

data class ProductResponse(

    @field:SerializedName("metadata")
	val metadata: Metadata? = null,

    @field:SerializedName("results")
	val results: Int? = null,

    @field:SerializedName("document")
	val data: List<ProductDto?>? = null,

    @field:SerializedName("totalPages")
	val totalPages: Int? = null,

    @field:SerializedName("message")
	val message: String? = null
)