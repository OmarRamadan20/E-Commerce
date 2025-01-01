package com.example.routee_commerce.ui.cart

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.base.ViewMessage
import com.example.routee_commerce.base.showDialog
import com.example.routee_commerce.databinding.ActivityCartBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    val adapter = CartAdapter()
    private val viewModel:CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observeLiveData()
        viewModel.doAction(CartContract.Action.GetCart(getToken()!!))
    }

    private fun initViews() {
        binding.content.cartRv.adapter = adapter
    }
    private fun getToken(): String? {
        val token = this.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return token.getString("user_token", null)
    }


    private fun observeLiveData() {

        viewModel.event.observe(this){
            when(it){
                is CartContract.Event.ShowMessage -> {
                    showDialog(it.viewMessage)
                }

            }
        }

        lifecycleScope.launch {
            viewModel.state.collect {state->

                when(state){
                    is CartContract.ViewState.Error -> {
                        Log.e("Cart","error to get  cart")
                        showDialog(
                            ViewMessage(
                            message = "Fail to get  cart", posActionName = "Ok")
                        )
                    }
                    is CartContract.ViewState.Loading -> {
                    }
                    is CartContract.ViewState.Success -> {
                        Log.e("Cart","success")
                        adapter.bindCartItemsList(state.products?: emptyList())
                    }

                    CartContract.ViewState.Idle ->{

                    }
                }
            }
        }
    }


}
