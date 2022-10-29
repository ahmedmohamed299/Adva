package com.example.adva.presentation.ui.splash

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.adva.R
import com.example.adva.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
   private var logoAnim: Animation? = null
    private lateinit var binding:FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_splash,container,false)
        fullScreen()

        anim()

        return binding.root
    }


    private fun fullScreen() {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

    }

    private fun anim() {

        logoAnim = AnimationUtils.loadAnimation(requireActivity(), R.anim.logo_anim)
        binding.splashLogo.animation = logoAnim
        binding.splashLogo.animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
         }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }





    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar!!.show()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
            )
        }
    }
}