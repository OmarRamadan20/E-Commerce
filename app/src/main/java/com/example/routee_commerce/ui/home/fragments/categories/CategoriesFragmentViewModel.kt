package com.example.routee_commerce.ui.home.fragments.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.base.BaseViewModel
import com.example.routee_commerce.base.ViewMessage
import com.route.domain.common.MyResult
import com.route.domain.usecase.GetCategoriesUseCase
import com.route.domain.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesFragmentViewModel @Inject constructor(
    private val cagtegoriesUSeCse: GetCategoriesUseCase,
    private val getProductsByCategoryUseCase: ProductsUseCase
) : BaseViewModel(),CategoriesContract.ViewModel {
    
    private val _viewState = MutableStateFlow<CategoriesContract.ViewState>(CategoriesContract.ViewState.Loading)
    
    override val state: StateFlow<CategoriesContract.ViewState>
        get() = _viewState
    
    private val _event = MutableLiveData<CategoriesContract.Event>()
    override val event: LiveData<CategoriesContract.Event>
        get() = _event


    override fun doAction(action: CategoriesContract.Action) {
        when(action){
            CategoriesContract.Action.InitPage -> {
                getCategories()
            }
            is CategoriesContract.Action.GetProducts -> {
                getProductsByCategory(action.categoryId)
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            cagtegoriesUSeCse.invoke().collect {result->
                when(result){
                    is MyResult.Failure -> {
                        Log.e("tt","failure")
                        _viewState.emit(CategoriesContract.ViewState.Error(
                            ViewMessage(result.exception.localizedMessage?:"Something went wrong")
                        ))
                    }
                    is MyResult.Loading -> {
                        Log.e("tt","loading")
                        _viewState.emit(CategoriesContract.ViewState.Loading)
                    }
                    is MyResult.ServerFail -> {
                        Log.e("tt","sever fail")

                        _viewState.emit(
                            CategoriesContract.ViewState.Error(
                                ViewMessage(result.serverError.message ?: "Something went wrong")
                            )
                        )
                    }
                    is MyResult.Success -> {
                        Log.e("tt","success")
                        _viewState.emit(CategoriesContract.ViewState.Success(result.data?: emptyList()))
                    }
                }
            }
        }
        
    }

    private fun getProductsByCategory(categoryId:String){
        viewModelScope.launch {
            getProductsByCategoryUseCase.invoke(categoryId).collect{ result->
                when(result){
                    is MyResult.Failure -> {
                        Log.e("result","fail categories")
                        Log.e("result","$result")
                        _viewState.emit(CategoriesContract.ViewState.Error(
                            ViewMessage(result.exception.localizedMessage?:"Something went wrong")
                        ))
                    }
                    is MyResult.Loading -> {
                        Log.e("tt","loading")
                        _viewState.emit(CategoriesContract.ViewState.Loading)
                    }
                    is MyResult.ServerFail -> {
                        Log.e("tt","sever fail")

                        _viewState.emit(
                            CategoriesContract.ViewState.Error(
                                ViewMessage(result.serverError.message ?: "Something went wrong")
                            )
                        )
                    }
                    is MyResult.Success -> {
                        Log.e("result","success categories")
                        Log.e("result","$result")

                        _viewState.emit(CategoriesContract.ViewState.GetProducts(result.data!!))
                    }
                }
            }
        }


    }
}