package com.dkitamura.crave.repo

import com.dkitamura.crave.models.network.recipeinformation.RecipeInformationResponse
import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RecipeInformationRepoImpl @Inject constructor(private val recipeApi: RecipeApi)
    : RecipeInformationRepo {

    override suspend fun getRecipeInformationById(id: Int): Flow<Result<RecipeInformationResponse>> {
        return flow {
            emit(Result.InProgress)

            try {
                val result = recipeApi.getRecipeInformationById(id)
                if(result.isSuccessful) {
                    emit(Result.Success(result.body()!!))
                } else {
                    emit(Result.Error(Exception("Some error")))
                }

            } catch (exception: Exception) {
                emit(Result.Error(exception))
            }
        }
    }
}