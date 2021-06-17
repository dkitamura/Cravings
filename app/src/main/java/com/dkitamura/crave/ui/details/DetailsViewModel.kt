package com.dkitamura.crave.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkitamura.crave.models.network.recipeinformation.RecipeInformationResponse
import com.dkitamura.crave.network.Result
import com.dkitamura.crave.repo.RecipeInformationRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
   private val recipeInformationRepo: RecipeInformationRepo
): ViewModel() {

    private val _recipeInformationFlow = MutableStateFlow<Result<RecipeInformationResponse>>(Result.InProgress)
    val recipeInformationFlow : StateFlow<Result<RecipeInformationResponse>> =  _recipeInformationFlow

    fun getRecipeInformation(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            recipeInformationRepo.getRecipeInformationById(id).collect {
                _recipeInformationFlow.value = it
            }
        }

    }
}