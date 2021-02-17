package com.dkitamura.crave.repo

import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.Result

interface RandomRecipeRepo {
    suspend fun getRecipes(amount: Int): Result<List<Recipe>>
}