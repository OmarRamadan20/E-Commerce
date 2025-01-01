package com.example.routee_commerce.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.base.ViewMessage
import com.route.domain.common.MyResult
import com.route.domain.usecase.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel@Inject constructor
    (val getCartUseCase: GetCartUseCase): ViewModel(),CartContract.ViewModel {


    private val _viewState = MutableStateFlow<CartContract.ViewState>(CartContract.ViewState.Idle)
    override val state: StateFlow<CartContract.ViewState>
        get() = _viewState

    private val _event = MutableLiveData<CartContract.Event>()
    override val event: LiveData<CartContract.Event>
        get() = _event


    override fun doAction(action: CartContract.Action) {
        when (action) {
            is CartContract.Action.GetCart -> getCart(action.token)
        }
    }

    private fun getCart(token: String) {
        viewModelScope.launch {
            when (val response = getCartUseCase.invoke(token)) {
                is MyResult.Failure -> {
                    Log.e("Cart", "Failure to get cart: ${response.exception.localizedMessage}")

                    _viewState.emit(
                        CartContract.ViewState.Error(
                            ViewMessage(
                                response.exception.localizedMessage ?: "Something went wrong"
                            )
                        )
                    )
                }

                is MyResult.Loading -> {
                    Log.e("tt", "loading")
                    _viewState.emit(CartContract.ViewState.Loading)
                }

                is MyResult.ServerFail -> {
                    Log.e("tt", "sever fail")

                    _viewState.emit(
                        CartContract.ViewState.Error(
                            ViewMessage(response.serverError.message ?: "Something went wrong")
                        )
                    )
                }

                is MyResult.Success -> {
                    Log.e("Cart", "success to get cart")
//                    val res = response.data.cart?.map{
//                        it?.products
//                    }
//                    val r = res?.map {
//                        it?.map {
//                            it?.productId
//                        }
//                    }
//                    val n= r?.map {list->
//                        list?.map{
//                            it?.toProduct()
//                        }
//                    }

                    _viewState.emit(CartContract.ViewState.Success(response.data))

                }
            }
        }
    }
}
