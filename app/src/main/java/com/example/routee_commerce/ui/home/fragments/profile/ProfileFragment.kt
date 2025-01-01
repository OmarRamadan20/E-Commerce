package com.example.routee_commerce.ui.home.fragments.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.routee_commerce.databinding.FragmentProfileBinding
import com.example.routee_commerce.ui.userAuthentication.activity.UserAuthenticationActivity


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun logout() {
        // Clear SharedPreferences or any stored data
        val token = context?.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)


        val editor = token?.edit()
        val t = token?.getString("user_token", null)!!
        Log.e("Token before logout", t)
        editor?.clear() // Clear all user-related data
        editor?.apply()

        val t2 = token.getString("user_token", null)


        Log.e("Token after logout", t2.toString())

        val intent = Intent(requireContext(), UserAuthenticationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logOutBtn.setOnClickListener {
            logout()
        }
    }
}
