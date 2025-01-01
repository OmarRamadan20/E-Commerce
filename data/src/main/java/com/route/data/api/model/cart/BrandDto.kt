package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.domain.models.Brand
import com.route.domain.models.Image

data class BrandDto(

    @field:SerializedName("image")
    val image: Image? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("_id")
    val id: String? = null
){
    fun toBrand(): Brand {
        return Brand(
            image, name, id
        )
    }
}