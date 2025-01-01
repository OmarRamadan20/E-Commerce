package com.example.routee_commerce.ui.home.fragments.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.base.BaseViewModel
import com.example.routee_commerce.base.ViewMessage
import com.route.domain.common.MyResult
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.wishlist.WishListRequest
import com.route.domain.usecase.AddToCartUseCase
import com.route.domain.usecase.UpdateWishlistUseCase
import com.route.domain.usecase.CombineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val combineUseCase: CombineUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val updateWishlistUseCase: UpdateWishlistUseCase
):BaseViewModel(),HomeContract.ViewModel {


    private val _viewState = MutableStateFlow<HomeContract.ViewState>(HomeContract.ViewState.Loading)
    override val state: StateFlow<HomeContract.ViewState>
        get() = _viewState

    private val _event = MutableSharedFlow<HomeContract.Event>()
    override val event: SharedFlow<HomeContract.Event>
        get() = _event


    override fun doAction(action: HomeContract.Action) {
        when(action){
            is HomeContract.Action.InitPage -> {
               initPage()
            }
            is HomeContract.Action.AddToCart -> {
                addToCart(action.addToCartRequest,action.token)
            }

            is HomeContract.Action.AddToWishList -> {
                addToWishList(action.wishListRequest,action.token)
            }
        }
    }

    private fun initPage() {
        getData()
    }

    private fun getData(category:String?=null) {
        viewModelScope.launch(Dispatchers.IO) {
            combineUseCase.invoke(category).collect{ result ->
                when (result) {
                    is MyResult.Failure ->{
                        Log.e("addToCart", "Failure: ${result.exception.localizedMessage}")
                        _viewState.emit(HomeContract.ViewState.Error(
                            ViewMessage(result.exception.localizedMessage?:"Something went wrong")))
                    }
                    is MyResult.Loading -> {
                        Log.e("tt","loading")
                        _viewState.emit(HomeContract.ViewState.Loading)
                    }
                    is MyResult.ServerFail -> {
                        Log.e("tt","sever fail")

                        _viewState.emit(
                            HomeContract.ViewState.Error(
                                ViewMessage(result.serverError.message ?: "Something went wrong")
                            )
                        )
                    }
                    is MyResult.Success->{
                        Log.e("tt","success")
                        _viewState.emit(HomeContract.ViewState.Success(result.data))

                    }
                }
            }

        }
    }

    private fun addToCart(addToCartRequest: AddToCartRequest,token:String){
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = addToCartUseCase.invoke(addToCartRequest,token)) {
                is MyResult.Failure -> {
                    Log.e("tt", "failure clicked add to cart")
                    _viewState.emit(
                        HomeContract.ViewState.Error(
                            ViewMessage(
                                response.exception.localizedMessage
                                    ?: "Something went wrong"
                            )
                        )
                    )
                }
                is MyResult.Loading -> {
                    Log.e("tt", "loading")
                    _viewState.emit(HomeContract.ViewState.Loading)
                }
                is MyResult.ServerFail -> {
                    Log.e("tt", "sever fail to add to cart")
                    _viewState.emit(
                        HomeContract.ViewState.Error(
                            ViewMessage(response.serverError.message ?: "Something went wrong")
                        )
                    )
                }
                is MyResult.Success ->{
                    Log.e("tt","Clicked Add To Cart success")
                    _viewState.emit(HomeContract.ViewState.AddToCart(addToCartRequest,token))
                }
            }
        }
    }

    private fun addToWishList(wishListRequest: WishListRequest, token:String){
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = updateWishlistUseCase.invoke(wishListRequest,token)) {
                is MyResult.Failure -> {
                    Log.e("Add to wishlist", "failure clicked add to wish list")
                    _viewState.emit(
                        HomeContract.ViewState.Error(
                            ViewMessage(
                                response.exception.localizedMessage
                                    ?: "Something went wrong"
                            )
                        )
                    )

                }
                is MyResult.Loading -> {
                    Log.e("tt", "loading")
                    _viewState.emit(HomeContract.ViewState.Loading)
                }
                is MyResult.ServerFail -> {
                    Log.e("Add to wishlist", "sever fail to add to Wishlist")
                    _viewState.emit(
                        HomeContract.ViewState.Error(
                            ViewMessage(response.serverError.message ?: "Something went wrong")
                        )
                    )
                }
                is MyResult.Success ->{
                    Log.e("Add To wishlist","Clicked Add To Wishlist success")
                    _viewState.emit(HomeContract.ViewState.AddToWishList(wishListRequest,token))
                }
            }
        }
    }
}