package com.dkitamura.crave.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dkitamura.crave.BuildConfig
import com.dkitamura.crave.R
import com.dkitamura.crave.ViewModelFactory
import com.dkitamura.crave.databinding.HomeFragmentBinding
import com.dkitamura.crave.extensions.autoCleared
import com.dkitamura.crave.network.Client

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory(this, Client(BuildConfig.API_KEY).build())
    }

    private var binding: HomeFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.text.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_testFragment)
        }

        viewModel.getRandomRecipes()
        viewModel.recipeResult.observe(viewLifecycleOwner, Observer {
            binding.text.text = it.recipes.toString()
        })
    }

}