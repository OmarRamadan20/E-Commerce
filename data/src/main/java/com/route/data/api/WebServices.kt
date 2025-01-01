package com.route.data.api

import com.route.data.api.model.CategoryResponse
import com.route.data.api.model.brands.BrandsResponse
import com.route.data.api.model.cart.AddToCartResponseDto
import com.route.data.api.model.product.ProductResponse
import com.route.data.api.model.wishlist.AddToWishListResponseDto
import com.route.data.api.model.wishlist.get_wishlist.WishListResponseDto
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.GetCartResponse
import com.route.domain.models.LoginRequest
import com.route.domain.models.LoginResponse
import com.route.domain.models.RegisterRequest
import com.route.domain.models.RegisterRespone
import com.route.domain.models.wishlist.WishListRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface WebServices {

    @GET("/api/v1/categories")
    suspend fun getAllCategories() : CategoryResponse

    @GET("/api/v1/product/all")
    suspend fun getProducts(
        @Query("category") category: String?=null
    ): ProductResponse

    @POST("/api/v1/user/signup")
    suspend fun signUp(@Body request: RegisterRequest): RegisterRespone

    @POST("/api/v1/user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("/api/v1/brands")
    suspend fun getAllBrands(): BrandsResponse

    @PUT("/api/v1/cart/add")
    suspend fun addToCart(@Body addToCartRequest: AddToCartRequest,
                          @Header("token") token: String,
                          @Header("Content-Type") contentType: String="application/json"  // Content-Type header

    ): AddToCartResponseDto

    @GET("/api/v1/cart")
    suspend fun getCartItems(@Header("token") token: String): GetCartResponse

    @PUT("/api/v1/user/wishlist")
    suspend fun updateWishlist(@Body wishListRequest: WishListRequest,
                              @Header("token") token: String
    ): AddToWishListResponseDto

    @GET("/api/v1/user/wishlist")
    suspend fun getWishList(@Header("token") token: String): WishListResponseDto
}