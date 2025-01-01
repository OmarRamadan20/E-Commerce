package com.example.routee_commerce.ui.userAuthentication.fragments.register

import com.example.routee_commerce.base.ViewMessage
import com.example.routee_commerce.ui.userAuthentication.fragments.login.LoginContract
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterContract {

    interface ViewModel{
        val viewState: StateFlow<ViewState>
        val event: SharedFlow<Event>

        fun doAction(action:Action)

    }

    sealed class ViewState{
        object Loading:ViewState()
        data class Success(val message: String) : ViewState()
        data class Error(val error: String) : ViewState()
        object NavigatingToLogin: ViewState()
        object Idle:ViewState()
    }

    sealed class Action{
        data class Register(val name: String ,val email: String, val password: String) : Action()
        data object GotoLogin:Action()
    }


    sealed class Event(){
        data class ShowMessage(val viewMessage: ViewMessage):Event()
    }
}