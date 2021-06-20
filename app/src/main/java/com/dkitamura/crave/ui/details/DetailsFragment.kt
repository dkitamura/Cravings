package com.dkitamura.crave.ui.details

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import coil.api.load
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
                is Result.Success -> {

                    with(binding) {

                        titleTextview.text = result.data.title
                        toolbar.title = result.data.title

                        recipeImageview.load(result.data.image)

                        summaryTextview.text = HtmlCompat.fromHtml(result.data.summary ?: "",
                            HtmlCompat.FROM_HTML_MODE_COMPACT).toString() + result.data.summary

                    }



                }
                Result.InProgress -> {}
                is Result.Error -> Log.d("Details", result.toString())
            }
        }

        viewmodel.getRecipeInformation(recipeId.itemId)
    }
}