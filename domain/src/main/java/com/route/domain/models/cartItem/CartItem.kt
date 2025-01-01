package com.route.domain.models.cartItem

import com.route.domain.models.Product

data class CartItem(
    val cartItemId: String?,

    val product: Product?,

    val quantity: Int?)
