package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesItem(

    val id: String? = null,
    val imageId: ImageId?=null, ):Parcelable