package com.example.routee_commerce.utils


import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.routee_commerce.R
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        @BindingAdapter("app:urlCircular")
        @JvmStatic
        fun bindImageCircular(imageView: ImageView, url: String?) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_category_placeholder)
                .transform(TransformCircular())
                .into(imageView)
        }

        @BindingAdapter("app:url")
        @JvmStatic
        fun bindImage(imageView: ImageView, url: String?) {
            Picasso.get()
                .load(url)
                .centerCrop()
                .fit()
                .into(imageView)
        }

    }
}
@BindingAdapter("app:Error")
fun setTextInputLayoutError(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
    textInputLayout.errorIconDrawable = null
}

@BindingAdapter("app:clearFocusOnCondition")
fun clearFocusOnCondition(view: View, condition: Boolean) {
    if (condition) {
        view.clearFocus()
    }
}

@BindingAdapter("app:errorText")
fun setError(editText: EditText, errorMessage: String?) {
    if (!errorMessage.isNullOrEmpty()) {
        editText.error = errorMessage
    } else {
        editText.error = null // Clear the error if the message is null
    }
}


//@BindingAdapter("app:onTextChanged")
//fun EditText.setOnTextChangedListener(onTextChanged: ((String) -> Unit)?) {
//    if (onTextChanged == null) return
//
//    addTextChangedListener(object : TextWatcher {
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            onTextChanged(s?.toString().orEmpty())
//        }
//
//        override fun afterTextChanged(s: Editable?) {}
//    })
//}
