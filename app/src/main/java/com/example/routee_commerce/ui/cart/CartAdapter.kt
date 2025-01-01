package com.example.routee_commerce.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routee_commerce.databinding.ItemCartBinding
import com.route.domain.models.Product
import com.route.domain.models.cartItem.CartItem

class CartAdapter(private var cartItemsList: List<Product?>? = null) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemCartBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(itemCartBinding.root) {

        fun bind(product: Product?) {
            itemCartBinding.product = product
            itemCartBinding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = cartItemsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = cartItemsList!![position]
        holder.bind(product)

    }

    fun bindCartItemsList(products: List<Product?>) {
        this.cartItemsList = products
        notifyDataSetChanged()
    }

}
