package com.dkitamura.crave

import com.dkitamura.crave.di.modules.RepoModule
import com.dkitamura.crave.repo.RandomRecipeRepo
import com.dkitamura.crave.repo.RecipeInformationRepo
import com.dkitamura.crave.repo.RecipeInformationRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [ActivityRetainedComponent::class],
    replaces = [RepoModule::class]
)
interface FakeRandomRecipeRepoModule {

    @Binds
    abstract fun bind(impl: FakeRandomRecipeRepoImpl): RandomRecipeRepo


    @Binds
    abstract fun provideRecipeInformationRepo(recipeRepo: RecipeInformationRepoImpl): RecipeInformationRepo
}