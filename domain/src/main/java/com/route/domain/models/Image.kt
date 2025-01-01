package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(

	val path: String? = null,

	val id: String? = null
):Parcelable