package com.dkitamura.crave

import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.Result
import com.dkitamura.crave.repo.RandomRecipeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration

class FakeRandomRecipeRepoImpl @Inject constructor() : RandomRecipeRepo {
    override suspend fun getRecipes(amount: Int): Result<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipesFlow(amount: Int): Flow<Result<List<Recipe>>> {
        return flow {
            emit(Result.InProgress)
            kotlinx.coroutines.delay(1000)
            emit(Result.Success(listOf(
                Recipe(title = "Test Recipe")
            )))
        }
    }
}