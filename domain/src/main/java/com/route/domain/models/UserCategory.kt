package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserCategory(

	val image: Image? = null,

	val name: String? = null,

	val id: String? = null
):Parcelable{
	fun toCategory():Category{
		return Category(name = name,id = id)

	}
}