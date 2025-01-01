package com.example.routee_commerce.ui.userAuthentication.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.base.BaseViewModel
import com.example.routee_commerce.base.ViewMessage
import com.route.domain.common.MyResult
import com.route.domain.models.LoginRequest
import com.route.domain.models.User
import com.route.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
):BaseViewModel(),LoginContract.ViewModel {



    private val _viewState = MutableStateFlow<LoginContract.ViewState>(LoginContract.ViewState.Idle)

    override val viewState: StateFlow<LoginContract.ViewState>
        get() = _viewState

    private val _event = MutableSharedFlow<LoginContract.Event>()

    override val event: SharedFlow<LoginContract.Event>
        get() = _event

    val emailLiveData = MutableLiveData<String>()
    val emailErrorLiveData = MutableLiveData<String>()

    val passwordLiveData = MutableLiveData<String>()
    val passwordErrorLiveData = MutableLiveData<String>()

    override fun doAction(action: LoginContract.Action) {
       viewModelScope.launch {
           when (action) {
               is LoginContract.Action.NavigateToRegister -> {
                   _viewState.value = LoginContract.ViewState.NavigatingToRegister
               }
               is LoginContract.Action.NavigateToHome -> {
                   if (isValidInputs()){
                       val request = LoginRequest(emailLiveData.value?:"", passwordLiveData.value!!?:"")
                       login(request)
                   }

               }
           }
       }
    }

    private fun login(request: LoginRequest) {
        viewModelScope.launch {
            _viewState.value = LoginContract.ViewState.Loading
            when(val result = loginUseCase.invoke(request)){

                is MyResult.Failure -> {
                    _viewState.emit(
                        LoginContract.ViewState.Error(
                            result.exception.message ?: "Unknown error occurred"
                        )
                    )
                    _event.emit(LoginContract.Event.ShowMessage(ViewMessage("Email or Password is incorrect")))
                }
                MyResult.Loading -> {

                }

                is MyResult.ServerFail -> {
                    _viewState.emit(
                        LoginContract.ViewState.Error(
                            result.serverError.localizedMessage ?: "Unknown error occurred"
                        )
                    )

                }

                is MyResult.Success -> {
                    _viewState.emit(
                        LoginContract.ViewState.Success(result.data.user?:User(),result.data.token)

                    )
                    _event.emit(LoginContract.Event.ShowMessage(ViewMessage("Login Successful")))


                }
            }
        }
    }
    fun isValidInputs():Boolean{
        var isValid = true

        val email = emailLiveData.value
        if (email.isNullOrBlank()){
            isValid = false
            emailErrorLiveData.value="Email is empty"
        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isValid = false
            emailErrorLiveData.value="Email is not valid or does not exist"
        }

        val password = passwordLiveData.value
        if (password.isNullOrBlank()){
            isValid = false
            passwordErrorLiveData.value="Password is empty"
        }
        else if (password.length < 8){
            passwordErrorLiveData.value = "Password is not valid or does not exist"
        }
        return isValid
    }
}