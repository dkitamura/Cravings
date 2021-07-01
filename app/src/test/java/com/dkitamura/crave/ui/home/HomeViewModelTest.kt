package com.dkitamura.crave.ui.home

import com.dkitamura.crave.network.Result
import com.dkitamura.crave.repo.RandomRecipeRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

import org.junit.Assert.*

class HomeViewModelTest {


    lateinit var homeViewModel: HomeViewModel

    @RelaxedMockK
    lateinit var randomRecipeRepo: RandomRecipeRepo

    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(randomRecipeRepo, testDispatcher)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When ViewModel is created then default values are emitted`() = runBlockingTest {
        assertTrue(homeViewModel.recipeFlow.value.isEmpty())
        assertFalse(homeViewModel.loaderStatusFlow.value)
        assertFalse(homeViewModel.errorFlow.value)
    }

    @Test
    fun `When randomRecipeRepo returns ResultError then errorFlow is true`() = runBlockingTest {

        coEvery {
            randomRecipeRepo.getRecipesFlow(any())
        } coAnswers {
            flow {
                emit(Result.Error(Exception("Test Exception")))
            }
        }
        homeViewModel.getRandomRecipes()
        assertTrue(homeViewModel.errorFlow.value)
    }

    @Test
    fun `When randomRecipeRepo returns ResultError then recipeList is empty`() = runBlockingTest {

        coEvery {
            randomRecipeRepo.getRecipesFlow(any())
        } coAnswers {
            flow {
                emit(Result.Error(Exception("Test Exception")))
            }
        }
        homeViewModel.getRandomRecipes()
        assertTrue(homeViewModel.recipeFlow.value.isEmpty())
    }

    @Test
    fun `When randomRecipeRepo returns ResultError then loader is false`() = runBlockingTest {

        coEvery {
            randomRecipeRepo.getRecipesFlow(any())
        } coAnswers {
            flow {
                emit(Result.Error(Exception("Test Exception")))
            }
        }
        homeViewModel.getRandomRecipes()
        assertFalse(homeViewModel.loaderStatusFlow.value)
    }


    @Test
    fun getLoaderStatusFlow() {
    }

    @Test
    fun getErrorFlow() {
    }

    @Test
    fun getRandomRecipes() {
    }

    @Test
    fun hideError() {
    }
}