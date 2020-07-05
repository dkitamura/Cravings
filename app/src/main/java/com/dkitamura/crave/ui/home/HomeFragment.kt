package com.dkitamura.crave.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dkitamura.crave.BuildConfig
import com.dkitamura.crave.ViewModelFactory
import com.dkitamura.crave.databinding.HomeFragmentBinding
import com.dkitamura.crave.extensions.autoCleared
import com.dkitamura.crave.network.Client
import com.dkitamura.crave.network.Result

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory(this, Client(BuildConfig.API_KEY).build())
    }

    private var binding: HomeFragmentBinding by autoCleared()

    private lateinit var homeEpoxyController: HomeEpoxyController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeEpoxyController = HomeEpoxyController()

        binding.homeEpoxyRecyclerview.run {
            setControllerAndBuildModels(homeEpoxyController)
        }


        viewModel.recipeResult.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> {
                    homeEpoxyController.setValues(isLoading = false, recipeList = it.data)
                }
                is Result.InProgress -> {
                    homeEpoxyController.setValues(isLoading = true)
                }
                is Result.Error -> TODO()
            }
        })
        viewModel.getRandomRecipes()

    }

}