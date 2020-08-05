package com.dkitamura.crave.ui.home

import androidx.hilt.Assisted
import androidx.lifecycle.MutableLiveData

import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.Result
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkitamura.crave.network.RecipeApi
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val api: RecipeApi
) : ViewModel() {

    var recipeResult: MutableLiveData<Result<List<Recipe>>> = MutableLiveData()


    fun getRandomRecipes() {
        viewModelScope.launch {
            recipeResult.postValue(Result.InProgress)
            val res = api.getRandomRecipes(5)
            if(res.isSuccessful) {
                recipeResult.postValue(Result.Success(res.body()?.recipes ?: ArrayList()))
            } else {
                recipeResult.postValue(Result.Success(ArrayList()))
            }
        }
    }
}