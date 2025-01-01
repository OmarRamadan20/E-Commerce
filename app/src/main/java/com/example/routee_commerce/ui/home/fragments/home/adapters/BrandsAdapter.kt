package com.example.routee_commerce.ui.home.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routee_commerce.databinding.ItemBrandBinding
import com.route.domain.models.Brand
import com.route.domain.models.Category

class BrandsAdapter(private var brands: List<Brand?>? = null) :
    RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    class ViewHolder(private val itemBrandBinding: ItemBrandBinding) :
        RecyclerView.ViewHolder(itemBrandBinding.root) {
        fun bind(brand: Brand) {
            itemBrandBinding.brand = brand
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBrandBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = brands?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brand = brands?.get(position)!!
        holder.bind(brand)
        if (!holder.itemView.hasOnClickListeners()) {
            brandClicked?.let { brandClicked ->
                holder.itemView.setOnClickListener {
                    brandClicked.invoke(position, brand)
                }
            }
        }

    }

    fun bindBrand(brands: List<Brand?>?) {
        this.brands = brands
        notifyDataSetChanged()
    }

    var brandClicked: ((position: Int, brand: Brand) -> Unit)? = null
}
