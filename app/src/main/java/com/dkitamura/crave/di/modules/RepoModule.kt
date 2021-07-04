package com.dkitamura.crave.di.modules

import com.dkitamura.crave.database.CravingsDatabase
import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.repo.RandomRecipeRepo
import com.dkitamura.crave.repo.RandomRecipeRepoImpl
import com.dkitamura.crave.repo.RecipeInformationRepo
import com.dkitamura.crave.repo.RecipeInformationRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideRandomRecipeRepo(randomRecipeRepo: RandomRecipeRepoImpl): RandomRecipeRepo

    @Binds
    abstract fun provideRecipeInformationRepo(recipeRepo: RecipeInformationRepoImpl): RecipeInformationRepo

}
