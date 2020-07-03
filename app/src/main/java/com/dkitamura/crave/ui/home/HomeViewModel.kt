package com.dkitamura.crave.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkitamura.crave.models.network.randomrecipes.RandomRecipeResponse
import com.dkitamura.crave.network.RecipeApi
import kotlinx.coroutines.launch

class HomeViewModel(private val api: RecipeApi) : ViewModel() {

    var recipeResult: MutableLiveData<RandomRecipeResponse> = MutableLiveData()


    fun getRandomRecipes() {
        viewModelScope.launch {
            val res = api.getRandomRecipes(5)
            if(res.isSuccessful) {
                recipeResult.value = res.body()
                Log.d("API", res.body().toString())
            }
        }
    }
}