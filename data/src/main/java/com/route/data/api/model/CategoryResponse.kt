package com.route.data.api.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(


    @field:SerializedName("document")
    val data: List<CategoryDto?>? = null,

    @field:SerializedName("totalPages")
    val totalPages: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("results")
	val results: Int? = null,

    @field:SerializedName("statusMsg")
	val statusMsg: String? = null,



)