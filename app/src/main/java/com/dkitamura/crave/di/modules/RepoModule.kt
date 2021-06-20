package com.dkitamura.crave.di.modules

import com.dkitamura.crave.database.CravingsDatabase
import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.repo.RandomRecipeRepo
import com.dkitamura.crave.repo.RandomRecipeRepoImpl
import com.dkitamura.crave.repo.RecipeInformationRepo
import com.dkitamura.crave.repo.RecipeInformationRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepoModule {

    @Provides
    fun provideRandomRecipeRepo(api: RecipeApi, database: CravingsDatabase): RandomRecipeRepo {
        return RandomRecipeRepoImpl(api, database)
    }

    @Provides
    fun provideRecipeInformationRepo(api: RecipeApi): RecipeInformationRepo {
        return RecipeInformationRepoImpl(api)
    }

}
