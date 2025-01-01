package com.route.domain.models


data class Cart(

	val createdAt: String? = null,

	val totalPrice: Any? = null,

	val userId: String? = null,

	val v: Int? = null,

	val id: String? = null,

	val products: List<ProductsItem?>? = null,

	val updatedAt: String? = null
)