package com.dkitamura.crave.repo

import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomRecipeRepoImpl @Inject constructor(
    private val recipeApi: RecipeApi
) : RandomRecipeRepo {

    override suspend fun getRecipes(amount: Int): Result<List<Recipe>> {
        val result = recipeApi.getRandomRecipes(amount)

        return try {
            if(result.isSuccessful) {
                Result.Success(result.body()?.recipes ?: emptyList())
            } else {
                Result.Error(Exception("Some network issue"))
            }
        } catch (throwable: Throwable) {
            Result.Error(throwable as Exception)
        }
    }

    override suspend fun getRecipesFlow(amount: Int): Flow<Result<List<Recipe>>> {

        return flow {
            emit(Result.InProgress)

            try {
                val result = recipeApi.getRandomRecipes(8)
                emit(Result.Success(result.body()?.recipes ?: emptyList()))
            } catch (exception: Exception) {
                emit(Result.Error(exception))
            }
        }
    }
}