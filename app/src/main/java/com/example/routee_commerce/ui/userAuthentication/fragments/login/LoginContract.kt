package com.example.routee_commerce.ui.userAuthentication.fragments.login

import androidx.lifecycle.LiveData
import com.example.routee_commerce.base.ViewMessage
import com.example.routee_commerce.ui.userAuthentication.fragments.register.RegisterContract
import com.route.domain.models.CombineData
import com.route.domain.models.User
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class LoginContract {


    interface ViewModel{
        val viewState: StateFlow<ViewState>
        val event : SharedFlow<Event>

        fun doAction(action:Action)

    }

    sealed class ViewState{
        object Idle:ViewState()
        object Loading:ViewState()
        object NavigatingToRegister : ViewState()
        data class Success(val user:User,val token:String) : ViewState()
        data class Error(val message: String) : ViewState()

    }
    sealed class Action{

        data object NavigateToRegister:Action()
        data class NavigateToHome(val email:String,val password:String):Action()

    }
    sealed class Event(){
        data class ShowMessage(val viewMessage: ViewMessage):Event()


    }
}