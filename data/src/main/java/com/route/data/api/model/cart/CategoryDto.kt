package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.domain.models.UserCategory

data class CategoryDto(

    @field:SerializedName("image")
    val imageDto: ImageDto? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("_id")
    val id: String? = null
){
    fun toCategory() : UserCategory {
        return UserCategory(
            id = id,
            name = name,
            image = imageDto?.toImage()
        )
    }

}