package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.domain.models.Image

data class ImageDto(

    @field:SerializedName("path")
    val path: String? = null,

    @field:SerializedName("_id")
    val id: String? = null
)
{
    fun toImage() = Image(path, id)
}