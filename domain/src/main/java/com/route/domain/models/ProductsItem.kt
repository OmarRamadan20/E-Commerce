package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductsItem(

	val quantity: Int? = null,

	val productId: ProductId? = null,

	val id: String? = null,
):Parcelable