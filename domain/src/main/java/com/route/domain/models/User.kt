package com.route.domain.models


data class User(

	val createdAt: String? = null,

	val password: String? = null,

	val role: String? = null,

	val wishlist: List<Any?>? = null,

	val v: Int? = null,

	val name: String? = null,

	val id: String? = null,

	val isActive: Boolean? = null,

	val email: String? = null,

	val isEmailVerified: Boolean? = null,

	val updatedAt: String? = null
)