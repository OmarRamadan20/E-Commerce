package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(

	val image: Image? = null,

	val name: String? = null,

	val id: String? = null,

):Parcelable