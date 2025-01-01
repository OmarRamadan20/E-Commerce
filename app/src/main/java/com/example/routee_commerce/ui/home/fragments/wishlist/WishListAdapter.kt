package com.example.routee_commerce.ui.home.fragments.wishlist

import android.animation.ObjectAnimator
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.ItemWishlistBinding
import com.route.domain.models.wishlist.getWishlist.WishlistItem

class WishListAdapter(private val token:String,
                      private var items:List<WishlistItem?>?=null,
                      private val onCartClick: ((actionType: ActionType,productId: String, token: String) -> Unit)? = null
    ):RecyclerView.Adapter<WishListAdapter.ViewHolder>() {


    enum class ActionType {
        ADD_TO_CART,
        REMOVE_FROM_WISHLIST
    }
    class ViewHolder(var viewBinding:ItemWishlistBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: WishlistItem){
            viewBinding.itemTitle.text = item.title
            viewBinding.productPrice.text = "EGP "+ item.price.toString()
            Glide.with(itemView.context).load(item.imgCover?.path)
                .placeholder(R.drawable.wish_list_placeholder).into(viewBinding.itemImage)
        }

        fun updateLikeButtonColor(isInWishlist: Boolean) {
            if (isInWishlist) {
                viewBinding.likeImage.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.red
                    ),
                    PorterDuff.Mode.SRC_IN
                )
            } else {
                viewBinding.likeImage.clearColorFilter()
            }
        }
        fun animateImageClick() {
            val scaleX = ObjectAnimator.ofFloat(viewBinding.likeImage, "scaleX", 1.2f, 1.0f)
            val scaleY = ObjectAnimator.ofFloat(viewBinding.likeImage, "scaleY", 1.2f, 1.0f)
            scaleX.duration = 400
            scaleY.duration = 400
            scaleX.start()
            scaleY.start()
        }
        fun animateCartClick() {
            val scaleX = ObjectAnimator.ofFloat(viewBinding.addToCartButton, "scaleX", 1.2f, 1.0f)
            val scaleY = ObjectAnimator.ofFloat(viewBinding.addToCartButton, "scaleY", 1.2f, 1.0f)
            scaleX.duration = 400
            scaleY.duration = 400
            scaleX.start()
            scaleY.start()
        }
    }


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemWishlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return ViewHolder(viewBinding)
     }

     override fun getItemCount(): Int {
         return items?.size?:0
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(items?.get(position)!!)
         val product = items!![position]
         holder.updateLikeButtonColor(true)
         holder.viewBinding.addToCartButton.setOnClickListener{
             holder.animateCartClick()
             onCartClick?.invoke(ActionType.ADD_TO_CART,product?.id!!, token)
             holder.viewBinding.addToCartButton.text = "Added"
             holder.viewBinding.addToCartButton.isEnabled = false
         }
         holder.viewBinding.likeImage.setOnClickListener{
             holder.animateImageClick()
             onCartClick?.invoke(ActionType.REMOVE_FROM_WISHLIST,product?.id!!, token)
             holder.updateLikeButtonColor(false)
             holder.viewBinding.likeImage.isEnabled=false
         }
      }

    fun bindItems(addedItems:List<WishlistItem?>?) {
        items = addedItems
        notifyDataSetChanged()
    }
}
