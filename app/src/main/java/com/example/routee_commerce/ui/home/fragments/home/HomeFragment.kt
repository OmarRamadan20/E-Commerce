package com.example.routee_commerce.ui.home.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.R
import com.example.routee_commerce.base.BaseFragment
import com.example.routee_commerce.base.ViewMessage
import com.example.routee_commerce.base.showDialog
import com.example.routee_commerce.databinding.FragmentHomeBinding
import com.example.routee_commerce.ui.home.fragments.home.adapters.BrandsAdapter
import com.example.routee_commerce.ui.home.fragments.home.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.home.adapters.ProductsAdapter
import com.route.domain.models.AddToCartRequest
import com.route.domain.models.Brand
import com.route.domain.models.Category
import com.route.domain.models.Product
import com.route.domain.models.wishlist.WishListRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeFragmentViewModel> (){
     private val categoriesAdapter = CategoriesAdapter()

    lateinit var mostSellingProductsAdapter : ProductsAdapter
     private val brandsAdapter = BrandsAdapter()

    private val mViewModel : HomeFragmentViewModel by viewModels()
    override fun initViewModel(): HomeFragmentViewModel {
        return mViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()
        viewModel.doAction(HomeContract.Action.InitPage)

    }

    private fun observeLiveData() {

        lifecycleScope.launch {
            viewModel.event.collect{
                when(it){
                    is HomeContract.Event.ShowMessage -> {
                        showDialog(it.viewMessage)
                    }
                }
            }

        }

        lifecycleScope.launch {
            viewModel.state.collect {state->

                when(state){
                    is HomeContract.ViewState.Error -> {
                        Log.e("tt","error to add to cart")
                        showDialog(ViewMessage(
                            message = "Fail to add to cart", posActionName = "Ok"))
                    }
                    is HomeContract.ViewState.Loading -> {
                        binding.categoriesShimmerViewContainer.isGone=false
                        binding.mostSellingProductsShimmerViewContainer.isGone=false
                        binding.brandsShimmerViewContainer.isGone=false
                    }
                    is HomeContract.ViewState.Success -> {
                        Log.e("tt","success")
                        binding.categoriesShimmerViewContainer.isGone=true
                        binding.mostSellingProductsShimmerViewContainer.isGone=true
                        binding.brandsShimmerViewContainer.isGone=true
                        showMostSellingProducts(state.combinedData
                            .mostSellingProducts?.shuffled()?.take(4))
                        showCategories(state.combinedData.categories)
                        showBrands(state.combinedData.brands)

                    }
                    is HomeContract.ViewState.AddToCart -> {
                        Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
                        }

                    is HomeContract.ViewState.AddToWishList -> {
                        Toast.makeText(requireContext(), "Added to Wishlist" , Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }

    private fun getToken(context: Context): String? {
        val token = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return token.getString("user_token", null)
    }

    private fun showBrands(brands: List<Brand?>?) {
        brandsAdapter.bindBrand(brands)
    }

    private fun showMostSellingProducts(mostSellingProducts: List<Product?>?) {
                mostSellingProductsAdapter.bindProducts(mostSellingProducts)
    }

    private fun showCategories(categories: List<Category?>?) {
               categoriesAdapter.bindCategories(categories)
    }


    private fun initViews() {
        categoriesAdapter.categoryClicked = { position, category ->
            navigateToCategoriesFragment(category)
        }

        mostSellingProductsAdapter = ProductsAdapter(getToken(requireContext())!!) { actionType, productId, token ->
            when (actionType) {
                ProductsAdapter.ActionType.ADD_TO_CART -> {
                    Log.e("Add to Cart", "Product ID: $productId")
                    viewModel.doAction(HomeContract.Action.AddToCart(AddToCartRequest(productId), token))
                }
                ProductsAdapter.ActionType.ADD_TO_WISHLIST -> {
                    Log.e("Add to Wishlist", "Product ID: $productId")
                    viewModel.doAction(HomeContract.Action.AddToWishList(WishListRequest(productId), token))
                }
            }
        }


        binding.categoriesRv.adapter = categoriesAdapter
        binding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
        binding.brandsRv.adapter = brandsAdapter
        binding.brandsNameTv.text = getString(R.string. brands)
//        categoryProductsAdapter.bindProducts()
//        mostSellingProductsAdapter.bindProducts()
//        categoriesAdapter.bindCategories()

    }


//    private fun navigateToProductDetailsFragment(product:Product) {
//        val intent = Intent(context, ProductDetailsActivity::class.java)
//        intent.putExtra(com.example.routee_commerce.model.Product.PRODUCT, product)
//        startActivity(intent)
//    }



    private fun navigateToCategoriesFragment(category: Category) {
//        val action = HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(category)
//        findNavController().navigate(action)

    }


}
