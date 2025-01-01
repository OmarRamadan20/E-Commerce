package com.example.routee_commerce.ui.userAuthentication.fragments.login

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.R
import com.example.routee_commerce.base.BaseFragment
import com.example.routee_commerce.databinding.FragmentLoginBinding
import com.example.routee_commerce.ui.home.activity.MainActivity
import com.example.routee_commerce.ui.userAuthentication.fragments.register.RegisterFragment
import com.example.routee_commerce.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding,LoginFragmentViewModel>() {
    private val mViewModel : LoginFragmentViewModel by viewModels()

    override fun initViewModel(): LoginFragmentViewModel {
        return mViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        initViews()
        observeViewModel()
        binding.donTHaveAnAccountTv.setOnClickListener {
            viewModel.doAction(LoginContract.Action.NavigateToRegister)
        }
        binding.loginBtn.setOnClickListener {
            animateClickEffect(it)
            viewModel.doAction(LoginContract.Action
                .NavigateToHome(binding.emailEdt.text.toString()
                    ,binding.passwordEdt.text.toString()))
        }
    }

    private fun observeViewModel() {
       lifecycleScope.launch {
           viewModel.viewState.collect{state->
               when(state){
                   is LoginContract.ViewState.Loading -> {
                   }
                   is LoginContract.ViewState.NavigatingToRegister -> {
                       navigateToRegister()
                   }
                   is LoginContract.ViewState.Error -> {
                       showErrorView()
                   }
                   is LoginContract.ViewState.Success -> {
                       showSuccessView()
                       saveToken(state.token)
                       val intent = Intent(requireContext(), MainActivity::class.java)
                       startActivity(intent)
                       requireActivity().finish()
                   }

                   LoginContract.ViewState.Idle -> {

                   }
               }
           }
       }
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is LoginContract.Event.ShowMessage -> {
                        Toast.makeText(requireContext(), event.viewMessage.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun saveToken(token: String) {
        Log.e("Token", "saveToken: $token")
        val sharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("user_token", token).apply()
        Log.e("Token", "savedToken: $token")
    }



    private fun navigateToRegister() {
            parentFragmentManager.beginTransaction().
            replace(R.id.fragment_container,RegisterFragment()).commit()
    }


    private fun initViews() {
        binding.vm = mViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


    private fun showSuccessView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showErrorView() {
        binding.icNext.isVisible = true
        binding.progressBar.isVisible = false
    }

    private fun showLoadingView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }



    private fun hideKeyboard() {
        view?.hideKeyboard(activity as AppCompatActivity?)
    }

    private fun animateClickEffect(view: View) {
        // Scale down animation (simulating button click)
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f)

        // Scale up animation (reset to original size)
        val scaleUpX = ObjectAnimator.ofFloat(view, "scaleX", 1f)
        val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 1f)

        // Play both scale down and scale up animations together
        val animatorSet = android.animation.AnimatorSet()
        animatorSet.playTogether(scaleDownX, scaleDownY)

        animatorSet.duration = 100 // Duration for scale down animation
        animatorSet.start()

        // After scale down animation, start scale up animation
        animatorSet.addListener(object : android.animation.Animator.AnimatorListener {


            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                // Reset to original size after 100 milliseconds
                val resetAnimatorSet = android.animation.AnimatorSet()
                resetAnimatorSet.playTogether(scaleUpX, scaleUpY)
                resetAnimatorSet.duration = 100 // Duration for scale up animation
                resetAnimatorSet.start()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
                val resetAnimatorSet = android.animation.AnimatorSet()
                resetAnimatorSet.playTogether(scaleUpX, scaleUpY)
                resetAnimatorSet.duration = 100 // Duration for scale up animation
                resetAnimatorSet.start()
            }
        })
    }


}
