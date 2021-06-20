package com.dkitamura.crave.repo

import com.dkitamura.crave.database.CravingsDatabase
import com.dkitamura.crave.database.daos.RandomRecipeDao
import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import okhttp3.internal.wait
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class RandomRecipeRepoImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val db: CravingsDatabase
) : RandomRecipeRepo {

    override suspend fun getRecipes(amount: Int): Result<List<Recipe>> {
        val result = recipeApi.getRandomRecipes(amount)

        return try {
            if(result.isSuccessful) {

                db.randomRecipeDao().insertAll(result.body()?.recipes ?: emptyList())
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
            db.randomRecipeDao().getAllRecipes().collect { recipe ->
                val result = recipe.filter { it.createdAt.plusMinutes(5).isAfter(OffsetDateTime.now()) }
                if(result.size > 0) {
                    emit(Result.Success(recipe))
                } else {
                    db.randomRecipeDao().deleteAllEntries()


                    val networkResult = getRecipes(8)
                    when(networkResult) {
                        is Result.Success -> {}
                        Result.InProgress -> {}
                        is Result.Error -> {}
                    }
                }
            }
        }
//        return flow {
//            emit(Result.InProgress)
//
//            try {
//                val result = recipeApi.getRandomRecipes(8)
//                emit(Result.Success(result.body()?.recipes ?: emptyList()))
//            } catch (exception: Exception) {
//                emit(Result.Error(exception))
//            }
//        }
    }
}