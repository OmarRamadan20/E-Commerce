package com.example.routee_commerce.ui.home.fragments.categories

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.R
import com.example.routee_commerce.base.BaseFragment
import com.example.routee_commerce.base.showDialog
import com.example.routee_commerce.databinding.FragmentCategoriesBinding
import com.example.routee_commerce.ui.home.fragments.categories.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.categories.adapters.ProductsAdapter
import com.route.domain.models.Category
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, CategoriesFragmentViewModel>() {


    var receivedCategory: Category?=null
    private val mViewModel : CategoriesFragmentViewModel by viewModels()

    override fun initViewModel(): CategoriesFragmentViewModel {
        return mViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_categories
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receivedCategory = CategoriesFragmentArgs.fromBundle(requireArguments()).category
        Log.e("receivedCategory", "$receivedCategory")
        initViews()
        observeLiveData()

        viewModel.doAction(CategoriesContract.Action.InitPage)
    }
    private val categoriesAdapter= CategoriesAdapter()
    private var productAdapter = ProductsAdapter()




    private fun initCategoryCard(category: Category?) {
        Picasso.get()
            .load(category?.image?.path)
            .centerCrop()
            .fit()
            .into(binding.cardBgImv)
        binding.selectedCategoryName.text = category?.name
    }

    private fun getToken(context: Context): String? {
        val token = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return token.getString("user_token", null)
    }

    private fun initViews() {


        binding.categoriesRv.adapter = categoriesAdapter
        binding.productsRv.adapter = productAdapter

        categoriesAdapter.categoryClicked = { position, category ->
            viewModel.doAction(CategoriesContract.Action.GetProducts(category.id!!))
            initCategoryCard(category)
        }

//        subcategoriesAdapter.bindSubcategories() with subcategories of the first category in categories list



    }


    private fun observeLiveData() {

        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is CategoriesContract.Event.ShowMessage -> {
                    showDialog(it.viewMessage)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->

                when (state) {
                    is CategoriesContract.ViewState.Error -> {
                        showErrorView(state.viewMessage.message)
                    }

                    CategoriesContract.ViewState.Loading -> {
                        showLoadingView()
                    }

                    is CategoriesContract.ViewState.Success -> {
                        Log.e("Success Cat,", "${state.category}")
                        categoriesAdapter.bindCategories(state.category)
                        showSuccessView()

                    }

                    is CategoriesContract.ViewState.GetProducts -> {
                        Log.e("Success Products,", "${state.products}")
                        productAdapter.bindProducts(state.products)
                        showSuccessView()
                    }
                }
            }
        }
    }



    private fun showLoadingView() {
        binding.categoriesShimmerViewContainer.isVisible = true
        binding.categoriesShimmerViewContainer.startShimmer()
        binding.errorView.isVisible = false
        binding.successView.isVisible = false
    }


    private fun showSuccessView() {

        binding.successView.isVisible = true
        binding.errorView.isVisible = false
        binding.categoriesShimmerViewContainer.isVisible = false
        binding.categoriesShimmerViewContainer.stopShimmer()

    }


    private fun showErrorView(message: String) {
        binding.errorView.isVisible = true
        binding.successView.isVisible = false
        binding.categoriesShimmerViewContainer.isVisible = false
        binding.categoriesShimmerViewContainer.stopShimmer()

        binding.errorMessage.text = message
        binding.tryAgainBtn.setOnClickListener {
            //LoadCategories
        }

    }


    override fun onResume() {
        super.onResume()
        binding.categoriesShimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        binding.categoriesShimmerViewContainer.stopShimmer()
        super.onPause()

    }


}
