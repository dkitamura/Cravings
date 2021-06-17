package com.dkitamura.crave.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import com.dkitamura.crave.R
import com.dkitamura.crave.databinding.FragmentDetailsBinding
import com.dkitamura.crave.extensions.autoCleared
import com.dkitamura.crave.network.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding by autoCleared()
    private val viewmodel : DetailsViewModel by viewModels()

    private val recipeId: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("DetailsFragment", "Recipe ID: "+recipeId.itemId)

        viewmodel.recipeInformationFlow.asLiveData().observe(viewLifecycleOwner) {
            result ->

            when(result) {
                is Result.Success -> Log.d("Details",result.data.toString())
                Result.InProgress -> {}
                is Result.Error -> Log.d("Details", result.toString())
            }
        }

        viewmodel.getRecipeInformation(recipeId.itemId)
    }
}