package com.example.routee_commerce.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val viewMessage = MutableLiveData<ViewMessage>()
    val showLoading = MutableLiveData<Boolean>()

}
