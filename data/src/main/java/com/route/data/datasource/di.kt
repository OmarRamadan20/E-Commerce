package com.route.data.datasource

import com.route.data.contract.AddToCartDataSource
import com.route.data.contract.WishListDataSource
import com.route.data.contract.AuthDataSource
import com.route.data.contract.BrandsDataSource
import com.route.data.contract.CategoryOnlineDataSource
import com.route.data.contract.GetCartDataSource
import com.route.data.contract.GetWishListDataSource
import com.route.data.contract.ProductsOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OnlineDataSourceModule{
    @Binds
    abstract fun bindCategoriesOnlineDataSource(
        impl: CategoriesOnlineDataSourceImpl
    ): CategoryOnlineDataSource

    @Binds
    abstract fun bindProductsOnlineDataSource(
        impl: ProductsOnlineDataSourceImpl
    ): ProductsOnlineDataSource

    @Binds
    abstract fun bindRegisterOnlineDataSource(
        impl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    abstract fun bindBrandsOnlineDataSource(
        impl: BrandsDataSourceImpl
    ): BrandsDataSource

    @Binds
    abstract fun bindAddToCartOnlineDataSource(
        impl: AddToCartDataSourceImpl
    ): AddToCartDataSource

    @Binds
    abstract fun bindGetCartDataSource(
        impl: GetCartDataSourceImpl
    ): GetCartDataSource

    @Binds
    abstract fun bindAddToWishListDataSource(
        impl: WishListDataSourceImpl
    ): WishListDataSource


    @Binds
    abstract fun bindGetWishListDataSource(
        impl: GetWishListDataSourceImpl
    ): GetWishListDataSource


}

