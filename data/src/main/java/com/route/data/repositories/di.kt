package com.route.data.repositories

import com.route.domain.contract.AddToCartRepository
import com.route.domain.contract.WishListRepository
import com.route.domain.contract.AuthRepository
import com.route.domain.contract.BrandsRepository
import com.route.domain.contract.CategoriesRepository
import com.route.domain.contract.GetCartRepository
import com.route.domain.contract.GetWishListRepository
import com.route.domain.contract.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule{

    @Binds
    abstract fun bindCategoriesRepo(
        catgoriesRepo: CategoriesRepositoryImpl
    ): CategoriesRepository

    @Binds
    abstract fun bindProductsRepo(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductsRepository

    @Binds
    abstract fun bindAuthRepo(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindBrandRepo(
        brandRepositoryImpl: BrandsRepositoryImpl
    ): BrandsRepository

    @Binds
    abstract fun bindAddToCartRepo(
        addToCartRepositoryImpl: AddToCartRepositoryImpl
    ): AddToCartRepository

    @Binds
    abstract fun bindGetCartRepo(
        getCardRepositoryImpl: GetCartRepositoryImpl
    ): GetCartRepository

    @Binds
    abstract fun bindAddToWishListRepo(
        wishListRepositoryImpl: WishListRepositoryImpl
    ): WishListRepository

    @Binds
    abstract fun bindGetWishListRepository(
        impl: GetWishListRepositoryImpl
    ): GetWishListRepository




}