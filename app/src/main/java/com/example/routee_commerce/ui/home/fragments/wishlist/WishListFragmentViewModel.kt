package com.example.routee_commerce.ui.home.fragments.wishlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.base.ViewMessage
import com.route.domain.common.MyResult
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.wishlist.WishListRequest
import com.route.domain.usecase.AddToCartUseCase
import com.route.domain.usecase.GetWishListUseCase
import com.route.domain.usecase.UpdateWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListFragmentViewModel @Inject constructor(
    private val getWishlistUseCase: GetWishListUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val updateWishlistUseCase: UpdateWishlistUseCase
)
    : ViewModel(), WishListContract.ViewModel {


    private val _viewState =
        MutableStateFlow<WishListContract.ViewState>(WishListContract.ViewState.Idle)
    override val state: StateFlow<WishListContract.ViewState>
        get() = _viewState

    private val _event = MutableSharedFlow<WishListContract.Event>()
    override val event: SharedFlow<WishListContract.Event>
        get() = _event

    override fun doAction(action: WishListContract.Action) {
        when (action) {
            is WishListContract.Action.GetWishList -> {
                getWishList(action.token)
            }

            is WishListContract.Action.AddToCart -> {
                addToCart(action.addToCartRequest,action.token)
            }

            is WishListContract.Action.RemoveFromWishList ->{
              removeFromWishList(action.wishListRequest,action.token)
            }
        }
    }

    private fun getWishList(token: String) {
        viewModelScope.launch {
            getWishlistUseCase.invoke(token).collect { response ->
                when (response) {
                    is MyResult.Failure -> {
                        Log.e(
                            "WishListViewModel",
                            "Failure: ${response.exception.localizedMessage}"
                        )
                        _viewState.emit(
                            WishListContract.ViewState.Error(
                                ViewMessage(
                                    response.exception.localizedMessage ?: "Something went wrong"
                                )
                            )
                        )
                    }

                    is MyResult.Loading -> {
                        Log.e("ViewModel Wishlist", "loading")
                        _viewState.emit(WishListContract.ViewState.Loading("Loading"))  // Set loading state

                    }

                    is MyResult.ServerFail -> {
                        Log.e("tt", "sever fail")

                        _viewState.emit(
                            WishListContract.ViewState.Error(
                                ViewMessage(response.serverError.message ?: "Something went wrong")
                            )
                        )
                    }

                    is MyResult.Success -> {
//                    Log.d("WishListViewModel", "observeData: ${response.data}")
//                    _viewState.emit(WishListContract.ViewState.Success(response.data))
                        if (response.data!!.isEmpty()) {
                            Log.d("WishListViewModel", "Data is empty or null")
                            _viewState.emit(
                                WishListContract.ViewState.Error(
                                    ViewMessage("No items in wishlist.")
                                )
                            )
                        } else {
                            Log.d("WishListViewModel", "${response.data}")

                            _viewState.emit(WishListContract.ViewState.Success(response.data))  // Emit Success state
                        }

                    }
                }
            }

        }
    }


    private fun addToCart(addToCartRequest: AddToCartRequest, token:String){
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = addToCartUseCase.invoke(addToCartRequest,token)) {
                is MyResult.Failure -> {
                    Log.e("clicked on add to cart (faiil) ", "$response")
                    _viewState.emit(
                        WishListContract.ViewState.Error(
                            ViewMessage("Something went wrong")
                        )
                    )
                }
                is MyResult.Loading -> {
                    Log.e("tt", "loading")
                    _viewState.emit(WishListContract.ViewState.Loading("Loading"))
                }
                is MyResult.ServerFail -> {
                    Log.e("tt", "sever fail to add to cart")
                    _viewState.emit(
                        WishListContract.ViewState.Error(
                            ViewMessage(response.serverError.message ?: "Something went wrong")
                        )
                    )
                }
                is MyResult.Success ->{
                    Log.e("tt","Clicked Add To Cart success")
                    _viewState.emit(WishListContract.ViewState.AddToCart(addToCartRequest,token))
                }
            }
        }
    }


    private fun removeFromWishList(wishListRequest: WishListRequest, token:String){
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = updateWishlistUseCase.invoke(wishListRequest,token)) {
                is MyResult.Failure -> {
                    Log.e("Add to wishlist", "failure clicked add to wish list")
                    _viewState.emit(
                        WishListContract.ViewState.Error(
                            ViewMessage(
                                response.exception.localizedMessage
                                    ?: "Something went wrong"
                            )
                        )
                    )

                }
                is MyResult.Loading -> {
                    Log.e("tt", "loading")
                    _viewState.emit(WishListContract.ViewState.Loading("Loading"))
                }
                is MyResult.ServerFail -> {
                    Log.e("Add to wishlist", "sever fail to add to Wishlist")
                    _viewState.emit(
                        WishListContract.ViewState.Error(
                            ViewMessage(response.serverError.message ?: "Something went wrong")
                        )
                    )
                }
                is MyResult.Success ->{
                    Log.e("Add To wishlist","Clicked Add To Wishlist success")
                    _viewState.emit(WishListContract.ViewState.RemoveFromWishList(wishListRequest,token))
                }
            }
        }
    }



}
