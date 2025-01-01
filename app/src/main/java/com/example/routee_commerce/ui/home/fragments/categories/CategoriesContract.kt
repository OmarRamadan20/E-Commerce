package com.example.routee_commerce.ui.home.fragments.categories

import androidx.lifecycle.LiveData
import com.example.routee_commerce.base.ViewMessage
import com.route.domain.models.Category
import com.route.domain.models.Product
import kotlinx.coroutines.flow.StateFlow

class CategoriesContract {
    interface ViewModel{
        val state: StateFlow<ViewState>
        val event : LiveData<Event>

        fun doAction(action:Action)

    }

    sealed class ViewState{
        object Loading:ViewState()

        data class Success(val category: List<Category?>):ViewState()

        data class Error(val viewMessage: ViewMessage):ViewState()

        data class GetProducts(val products: List<Product?>):ViewState()


    }
    sealed class Action{

        data object InitPage :Action()
        data class GetProducts(val categoryId:String):Action()

    }
    sealed class Event(){
        data class ShowMessage(val viewMessage: ViewMessage):Event()


    }
}