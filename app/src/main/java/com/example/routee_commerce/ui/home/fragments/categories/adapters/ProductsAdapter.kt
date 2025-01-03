package com.example.routee_commerce.ui.home.fragments.categories.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.routee_commerce.databinding.ItemProductBinding
import com.route.domain.models.CartRequest
import com.route.domain.models.Product

class ProductsAdapter(//private val token:String,
                      private var products: List<Product?>? = null,):
                      //private val onAddToCartClick: (productId: String, token: String) -> Unit):
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(val itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root) {

        fun bind(product:Product?) {
            itemProductBinding.product = product
            itemProductBinding.executePendingBindings()
            if (product?.priceAfterDiscount != null) {
                itemProductBinding.productPrice.text = "EGP ${product.priceAfterDiscount}"
                itemProductBinding.productOldPrice.isVisible = true
                itemProductBinding.productOldPrice.text = "EGP ${product.price}"
                itemProductBinding.productOldPrice.paintFlags =
                    itemProductBinding.productOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                itemProductBinding.productPrice.text = "EGP ${product?.price}"
                itemProductBinding.productOldPrice.isVisible = false
            }
            itemProductBinding.reviewValueTv.text = "(${product?.rateCount})"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = products?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products!![position]
        holder.bind(product)
        addProductToWishListClicked?.let {
            holder.itemProductBinding.addToWishlistBtn.setOnClickListener {
                addProductToWishListClicked?.invoke(product!!)
            }
        }
//        addProductToCartClicked?.let {
//            holder.itemProductBinding.addToCartBtn.setOnClickListener {
//                addProductToCartClicked?.invoke(CartRequest(product!!.id))
//            }
//        }
        holder.itemProductBinding.addToCartBtn.setOnClickListener {
       //     onAddToCartClick(product?.id!!, token)
        }
    }


    fun bindProducts(products: List<Product?>?) {
        this.products = products
        notifyDataSetChanged()
    }

    var addProductToWishListClicked: ((product:Product) -> Unit)? = null
    var addProductToCartClicked: ((cartRequest: CartRequest) -> Unit)? = null

}
