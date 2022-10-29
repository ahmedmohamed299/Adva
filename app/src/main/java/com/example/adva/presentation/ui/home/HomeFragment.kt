package com.example.adva.presentation.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.adva.R
import com.example.adva.data.utils.Resource
import com.example.adva.databinding.FragmentHomeBinding
import com.example.adva.presentation.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {


    private var adapter = ImagesAdapter()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel

        recyclerInitAndGetData()
        onBackPressed()

        return binding.root
    }


    private fun recyclerInitAndGetData() {
        binding.imageRecycler.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MainLoadStateAdapter(),
            footer = MainLoadStateAdapter()
        )


        viewModel.imagesList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    lifecycleScope.launch(Dispatchers.IO) {
                        it.data!!.collectLatest { imageItemPagingData ->
                            adapter.submitData(viewLifecycleOwner.lifecycle, imageItemPagingData)
                        }


                    }

                }
                is Resource.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "onCreateView: ${it.message}")
                }
            }
        }

    }

    private fun onBackPressed() {

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (Navigation
                        .findNavController(requireActivity(), R.id.fragment_container)
                        .currentDestination?.id == R.id.homeFragment
                ) {

                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            onBackPressedCallback
        )

    }

}