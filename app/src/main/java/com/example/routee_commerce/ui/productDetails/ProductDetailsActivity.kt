package com.example.routee_commerce.ui.productDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.ActivityMainBinding
import com.example.routee_commerce.databinding.ActivityProductDetailsBinding
import com.example.routee_commerce.databinding.FragmentProfileBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
