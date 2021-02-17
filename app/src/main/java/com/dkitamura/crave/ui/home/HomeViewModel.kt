package com.dkitamura.crave.ui.home

import android.util.Log
import androidx.hilt.Assisted
import androidx.lifecycle.MutableLiveData

import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.Result
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.repo.RandomRecipeRepo
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val randomRecipeRepo: RandomRecipeRepo
) : ViewModel() {

    var recipeResult: MutableLiveData<Result<List<Recipe>>> = MutableLiveData()


    fun getRandomRecipes() {
        viewModelScope.launch {
            recipeResult.postValue(Result.InProgress)

            val result = randomRecipeRepo.getRecipes(8)

            when(result) {
                is Result.Success -> {
                    recipeResult.postValue(Result.Success(result.data))
                }
                is Result.InProgress -> {
                    //
                }
                is Result.Error -> {
                    Log.e("HomeViewMode", result.toString())
                    recipeResult.postValue(Result.Success(emptyList()))
                }
            }
        }
    }
}