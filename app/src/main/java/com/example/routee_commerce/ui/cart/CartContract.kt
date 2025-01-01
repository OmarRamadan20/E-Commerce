package com.example.routee_commerce.ui.cart

import androidx.lifecycle.LiveData
import com.example.routee_commerce.base.ViewMessage
import com.route.domain.models.Product
import kotlinx.coroutines.flow.StateFlow

class CartContract {

    interface ViewModel{
        val state: StateFlow<ViewState>
        val event : LiveData<Event>

        fun doAction(action:Action)

    }

    sealed class ViewState{
        object Loading:ViewState()
        object Idle:ViewState()

        data class Success(val products: List<Product?>?):ViewState()

        data class Error(val viewMessage: ViewMessage):ViewState()


    }
    sealed class Action{

        data class GetCart(val token:String) :Action()

    }
    sealed class Event(){

        data class ShowMessage(val viewMessage: ViewMessage):Event()


    }
}