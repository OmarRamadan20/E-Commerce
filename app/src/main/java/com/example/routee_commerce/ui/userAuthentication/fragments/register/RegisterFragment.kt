package com.example.routee_commerce.ui.userAuthentication.fragments.register

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.routee_commerce.R
import com.example.routee_commerce.base.BaseFragment
import com.example.routee_commerce.databinding.FragmentRegisterBinding
import com.example.routee_commerce.ui.home.activity.MainActivity
import com.example.routee_commerce.ui.home.fragments.home.HomeFragmentViewModel
import com.example.routee_commerce.ui.userAuthentication.fragments.login.LoginFragment
import com.example.routee_commerce.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import com.route.domain.models.RegisterRequest
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding,RegisterFragmentViewModel>() {

    private val mViewModel : RegisterFragmentViewModel by viewModels()


    override fun initViewModel(): RegisterFragmentViewModel {
        return mViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_register
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        initViews()
        observeViewModel()

        binding.registerBtn.setOnClickListener {
            animateClickEffect(it)
            Log.e("register click ", "Register")
            mViewModel.doAction(RegisterContract.Action
                .Register(binding.userNameEdt.text.toString()
                    ,binding.emailEdt.text.toString()
                    ,binding.passwordEdt.text.toString()))
        }
        binding.loginDoHaveAccountTv.setOnClickListener {
            mViewModel.doAction(RegisterContract.Action.GotoLogin)
        }
    }

    private fun initViews() {
        binding.vm = mViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                when (state) {
                    is RegisterContract.ViewState.Loading -> {
                        showLoadingView()
                    }
                    is RegisterContract.ViewState.Success -> {
                        showSuccessView()

                        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment()).commit()

                    }
                    is RegisterContract.ViewState.Error -> {
                        showErrorView(state.error)
                    }

                    is RegisterContract.ViewState.Idle -> {
                    }

                    is RegisterContract.ViewState.NavigatingToLogin -> {
                        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment()).commit()
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is RegisterContract.Event.ShowMessage -> {
                        Toast.makeText(requireContext(), event.viewMessage.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun showSuccessView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showErrorView(message: String) {
        binding.icNext.isVisible = true
        binding.progressBar.isVisible = false
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()

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

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment()).commit()
        }
    }
}
