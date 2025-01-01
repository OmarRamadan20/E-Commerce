package com.example.routee_commerce.ui.userAuthentication.fragments.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.base.BaseViewModel
import com.example.routee_commerce.base.ViewMessage
import com.route.domain.common.MyResult
import com.route.domain.models.RegisterRequest
import com.route.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor
    (private val registerUseCase: RegisterUseCase):BaseViewModel(),RegisterContract.ViewModel {

    private val _viewState =
        MutableStateFlow<RegisterContract.ViewState>(RegisterContract.ViewState.Idle)

    override val viewState: StateFlow<RegisterContract.ViewState>
        get() = _viewState

    private val _event = MutableSharedFlow<RegisterContract.Event>()
    override val event: SharedFlow<RegisterContract.Event>
        get() = _event

    val nameLiveData = MutableLiveData<String?>()
    val nameErrorLiveData = MutableLiveData<String?>()

    val emailLiveData = MutableLiveData<String?>()
    val emailErrorLiveData = MutableLiveData<String?>()

    val passwordLiveData = MutableLiveData<String?>()
    val passwordErrorLiveData = MutableLiveData<String?>()

    val passwordConfirmationLiveData = MutableLiveData<String?>()
    val passwordConfirmationErrorLiveData = MutableLiveData<String?>()


    override fun doAction(action: RegisterContract.Action) {
        viewModelScope.launch {
            when (action) {
                is RegisterContract.Action.Register -> {
                    if (isValidateInputs()) {
                        val request=RegisterRequest(nameLiveData.value!!
                            ,emailLiveData.value!!
                            , passwordLiveData.value!!)
                        Log.e("register","${request.email} ${request.password}")
                        register(request)
                    }
                }

                RegisterContract.Action.GotoLogin -> {
                    _viewState.emit(RegisterContract.ViewState.NavigatingToLogin)
                }
            }
        }
    }



    private fun isValidateInputs(): Boolean {
        var isValid = true

        val name = nameLiveData.value
        if (name.isNullOrBlank()){
            isValid = false
            nameErrorLiveData.value="Please enter your name"
        }else if (name.length<4){
            isValid = false
            nameErrorLiveData.value="Please enter a valid name that at least 4 characters"
        }



        val email = emailLiveData.value
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (email.isNullOrBlank() || !email.matches(emailPattern.toRegex())){
            isValid = false
            emailErrorLiveData.value="Please enter a valid email address"
        }

        val password = passwordLiveData.value
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{6,}$";
        if (password.isNullOrBlank() || !password.matches(passwordPattern.toRegex())){
            isValid = false
            passwordErrorLiveData.value="Please enter a valid password that contains at least: " +
                    "\n-1 uppercase letter " +
                    "\n-1 lowercase letter " +
                    "\n-1 digit " +
                    "\n-1 special character " +
                    "\nAnd at least 6 characters."
        }

        val passwordConfirmation = passwordConfirmationLiveData.value
        if(passwordConfirmation.isNullOrBlank()){
            isValid = false
            passwordConfirmationErrorLiveData.value="Please enter your password confirmation"
        }
        else if (password != passwordConfirmation) {
            isValid = false
            passwordConfirmationErrorLiveData.value="Passwords do not match."
        }
        return isValid
    }


    private fun register(request: RegisterRequest) {

        viewModelScope.launch {
            _viewState.emit(RegisterContract.ViewState.Loading)
            when (val result = registerUseCase.invoke(request)) {

                is MyResult.Failure -> {
                    _viewState.emit(
                        RegisterContract.ViewState.Error(
                            result.exception.message ?: "Unknown error occurred"
                        )
                    )
                    _event.emit(RegisterContract.Event.ShowMessage(ViewMessage("Registration Fail")))
                }
                MyResult.Loading -> {

                }
                is MyResult.ServerFail -> {
                    _viewState.emit(
                        RegisterContract.ViewState.Error(
                            result.serverError.localizedMessage ?: "Unknown error occurred"
                        )
                    )
                    _event.emit(RegisterContract.Event.ShowMessage(ViewMessage("Registration Fail")))


                }
                is MyResult.Success -> {
                    _viewState.emit(
                        RegisterContract.ViewState.Success(result.data.status ?: "Register Succesfully")
                    )
                    _event.emit(RegisterContract.Event.ShowMessage(ViewMessage("Registration Successful")))
                }
            }
        }
    }
}
