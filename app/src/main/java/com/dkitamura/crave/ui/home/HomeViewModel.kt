package com.dkitamura.crave.ui.home


import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkitamura.crave.di.modules.DispatchersModule
import com.dkitamura.crave.repo.RandomRecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val randomRecipeRepo: RandomRecipeRepo,
    @DispatchersModule.coroutineIO private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {


    private val _recipeFlow = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeFlow: StateFlow<List<Recipe>> = _recipeFlow

    private val _loaderStatusFlow = MutableStateFlow<Boolean>(false)
    val loaderStatusFlow: StateFlow<Boolean> = _loaderStatusFlow

    private val _errorFlow = MutableStateFlow<Boolean>(false)
    val errorFlow: StateFlow<Boolean> = _errorFlow

    fun getRandomRecipes(amount: Int = 10) {

        viewModelScope.launch(dispatcher) {

            randomRecipeRepo.getRecipesFlow(amount)
                .collect {

                    when (it) {
                        is Result.Success -> {
                            _loaderStatusFlow.value = false
                            _recipeFlow.value = it.data
                        }

                        Result.InProgress -> {
                            _loaderStatusFlow.value = true
                        }

                        is Result.Error -> {
                            _recipeFlow.value = emptyList()
                            _loaderStatusFlow.value = false
                            _errorFlow.value = true
                        }
                    }
                }

        }
    }

    fun hideError() {
        _errorFlow.value = false
    }
}


