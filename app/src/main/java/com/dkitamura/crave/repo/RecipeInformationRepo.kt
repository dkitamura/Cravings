package com.dkitamura.crave.repo

import com.dkitamura.crave.models.network.recipeinformation.RecipeInformationResponse
import com.dkitamura.crave.network.Result
import kotlinx.coroutines.flow.Flow

interface RecipeInformationRepo {

    suspend fun getRecipeInformationById(id: Int): Flow<Result<RecipeInformationResponse>>
}