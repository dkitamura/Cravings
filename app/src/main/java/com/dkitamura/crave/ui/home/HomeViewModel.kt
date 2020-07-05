package com.dkitamura.crave.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.network.Result
import kotlinx.coroutines.launch

class HomeViewModel(private val api: RecipeApi) : ViewModel() {

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