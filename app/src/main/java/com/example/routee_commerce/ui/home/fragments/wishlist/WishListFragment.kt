package com.example.routee_commerce.ui.home.fragments.wishlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.databinding.FragmentWishlistBinding
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.wishlist.WishListRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishListFragment : Fragment() {
     private lateinit var viewBinding:FragmentWishlistBinding
    lateinit var adapter : WishListAdapter
    val viewModel : WishListFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentWishlistBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
        viewModel.doAction(WishListContract.Action.GetWishList(getToken(requireContext())!!))
    }
    private fun getToken(context: Context): String? {
        val token = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return token.getString("user_token", null)
    }


    private fun observeData() {
        lifecycleScope.launch {
            viewModel.state.collect{state->
                when(state) {
                    is WishListContract.ViewState.Error -> {
                        Log.d("WishListFragmentError", "observeData: ${state.viewMessage.message}")

                        showError(state.viewMessage.message)
                    }
                    is WishListContract.ViewState.Loading -> {
                        showLoading(state.message)
                    }
                    is WishListContract.ViewState.Idle -> {

                    }
                    is WishListContract.ViewState.Success -> {
                        hideLoading()
                        Log.d("WishListFragmentSuccess", "observeData: ${state.wishList}")
                        adapter.bindItems(state.wishList)
                    }
                    is WishListContract.ViewState.AddToCart->{
                        Log.e("Added to wishlist viewstate", "observeData")
                        Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
                    }

                    is WishListContract.ViewState.RemoveFromWishList ->{
                        Toast.makeText(requireContext(), "Removed from wishlist", Toast.LENGTH_SHORT).show()
                        viewModel.doAction(WishListContract.Action.GetWishList(getToken(requireContext())!!))
                    }
                }
            }
        }

    }

    private fun initView() {
        adapter=WishListAdapter(getToken(requireContext())!!){actionType, productId, token->
            when(actionType){
                WishListAdapter.ActionType.ADD_TO_CART -> {
                    viewModel.doAction(WishListContract.Action.AddToCart(AddToCartRequest(productId),token))
                }
                WishListAdapter.ActionType.REMOVE_FROM_WISHLIST ->{
                    viewModel.doAction(WishListContract.Action.RemoveFromWishList(WishListRequest(productId),token))
                }
            }
        }

        viewBinding.recyclerView.adapter = adapter

    }



    private fun showError(message: String) {
        viewBinding.errorView.isVisible = true
        viewBinding.successView.isVisible = false
        viewBinding.loadingView.isVisible = false
        viewBinding.errorText.text=message
        viewBinding.tryAgainButton.setOnClickListener{
            viewModel.doAction(WishListContract.Action.GetWishList(getToken(requireContext())!!))
        }
    }

    private fun showLoading(message: String) {
        viewBinding.errorView.isVisible = false
        viewBinding.successView.isVisible = false
        viewBinding.loadingView.isVisible = true
        viewBinding.errorText.text=message
    }
    private fun hideLoading() {
        viewBinding.errorView.isVisible = false
        viewBinding.successView.isVisible = true
        viewBinding.loadingView.isVisible = false
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.state.value is WishListContract.ViewState.Idle) {
            viewModel.doAction(WishListContract.Action.GetWishList(getToken(requireContext())!!))
        }
    }


}
