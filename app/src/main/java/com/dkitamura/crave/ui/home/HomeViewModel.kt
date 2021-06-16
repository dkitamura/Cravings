package com.dkitamura.crave.ui.home



import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkitamura.crave.repo.RandomRecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val randomRecipeRepo: RandomRecipeRepo
) : ViewModel() {


    private val _recipeFlow =  MutableStateFlow<Result<List<Recipe>>>(Result.Success(emptyList()))
    val recipeFlow: StateFlow<Result<List<Recipe>>> = _recipeFlow

    fun getRandomRecipes() {

        viewModelScope.launch {

         randomRecipeRepo.getRecipesFlow(8)
                .collect {

                when(it) {
                    is Result.Success -> _recipeFlow.value = Result.Success(it.data)

                    Result.InProgress -> {
                        _recipeFlow.value = Result.InProgress
                    }

                    is Result.Error -> {
                        _recipeFlow.value = Result.Success(emptyList())
                    }
                }
            }

//            recipeResult.postValue(Result.InProgress)
//
//            val result = randomRecipeRepo.getRecipes(8)
//
//            when(result) {
//                is Result.Success -> {
//                    recipeResult.postValue(Result.Success(result.data))
//                }
//                is Result.InProgress -> {
//                    //
//                }
//                is Result.Error -> {
//                    Log.e("HomeViewMode", result.toString())
//                    recipeResult.postValue(Result.Success(emptyList()))
//                }
            }
        }
    }
