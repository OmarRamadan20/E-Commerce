package com.example.routee_commerce.ui.home.fragments.wishlist

import com.example.routee_commerce.base.ViewMessage
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.wishlist.WishListRequest
import com.route.domain.models.wishlist.getWishlist.WishlistItem
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class WishListContract {

    interface ViewModel{
        val state: StateFlow<ViewState>
        val event : SharedFlow<Event>

        fun doAction(action:Action)

    }

    sealed class ViewState{
        data class Loading(val message:String):ViewState()
        object Idle:ViewState()
        data class Error(val viewMessage: ViewMessage):ViewState()

        data class Success(val wishList: List<WishlistItem?>?):ViewState()
        data class AddToCart(val addToCartRequest: AddToCartRequest, val token:String):ViewState()

        data class RemoveFromWishList(val wishListRequest: WishListRequest, val token:String):ViewState()




    }
    sealed class Action{

        data class GetWishList(val token:String):Action()
        data class AddToCart(val addToCartRequest: AddToCartRequest, val token:String):Action()
        data class RemoveFromWishList(val wishListRequest: WishListRequest, val token:String):Action()




    }
    sealed class Event(){
        data class ShowMessage(val viewMessage: ViewMessage):Event()


    }
}