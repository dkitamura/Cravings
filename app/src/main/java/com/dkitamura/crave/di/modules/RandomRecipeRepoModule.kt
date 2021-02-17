package com.dkitamura.crave.di.modules

import com.dkitamura.crave.network.RecipeApi
import com.dkitamura.crave.repo.RandomRecipeRepo
import com.dkitamura.crave.repo.RandomRecipeRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class RandomRecipeRepoModule {

    @Provides
    fun provideRandomRecipeRepo(api: RecipeApi): RandomRecipeRepo {
        return RandomRecipeRepoImpl(api)
    }

}
