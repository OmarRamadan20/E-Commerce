package com.example.routee_commerce.ui.home.fragments.home

import com.example.routee_commerce.base.ViewMessage
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.CombineData
import com.route.domain.models.wishlist.WishListRequest
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class HomeContract {


    interface ViewModel{
        val state: StateFlow<ViewState>
        val event : SharedFlow<Event>

        fun doAction(action:Action)

    }

    sealed class ViewState{
        object Loading:ViewState()

        data class Success(val combinedData:CombineData):ViewState()

        data class Error(val viewMessage: ViewMessage):ViewState()

        data class AddToCart(val addToCartRequest: AddToCartRequest , val token:String):ViewState()

        data class AddToWishList(val wishListRequest: WishListRequest, val token:String):ViewState()



    }
    sealed class Action{

        data object InitPage :Action()
        data class AddToCart(val addToCartRequest: AddToCartRequest,val token:String):Action()
        data class AddToWishList(val wishListRequest: WishListRequest, val token:String):Action()


    }
    sealed class Event(){
        data class ShowMessage(val viewMessage: ViewMessage):Event()


    }
}