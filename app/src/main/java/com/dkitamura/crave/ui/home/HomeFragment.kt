package com.dkitamura.crave.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.dkitamura.crave.databinding.HomeFragmentBinding
import com.dkitamura.crave.extensions.autoCleared
import com.dkitamura.crave.network.Result
import com.dkitamura.crave.ui.details.DetailsFragmentArgs
import com.dkitamura.crave.ui.home.epoxy.RecipeClickListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), RecipeClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    private var binding: HomeFragmentBinding by autoCleared()

    private lateinit var homeEpoxyController: HomeEpoxyController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeEpoxyController = HomeEpoxyController(this)

        binding.homeEpoxyRecyclerview.run {
            setControllerAndBuildModels(homeEpoxyController)
        }

        viewModel.loaderStatusFlow.asLiveData().observe(viewLifecycleOwner) { loaderVisibility ->
            homeEpoxyController.setLoadingStatus(loaderVisibility)
        }

        viewModel.errorFlow.asLiveData().observe(viewLifecycleOwner) { showError ->
            if(showError) {
                createNetworkErrorSnackbar()
            }
        }

        viewModel.recipeFlow.asLiveData().observe(viewLifecycleOwner) {
            recipeResult ->
            homeEpoxyController.setRecipeList(recipeResult)
        }


        viewModel.getRandomRecipes()
    }

    private fun createNetworkErrorSnackbar() {
        val snackbar = Snackbar.make(binding.root, "There has been an error, please try again.", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                viewModel.getRandomRecipes()
                viewModel.hideError()
            }
        snackbar.show()
    }

    override fun onRecipeClicked(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }

}