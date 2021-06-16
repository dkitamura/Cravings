package com.dkitamura.crave.repo

import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.Result
import kotlinx.coroutines.flow.Flow

interface RandomRecipeRepo {
    suspend fun getRecipes(amount: Int): Result<List<Recipe>>

    suspend fun getRecipesFlow(amount: Int): Flow<Result<List<Recipe>>>
}