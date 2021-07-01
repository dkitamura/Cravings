package com.dkitamura.crave.ui.home

import com.dkitamura.crave.models.network.randomrecipes.Recipe
import com.dkitamura.crave.network.Result
import com.dkitamura.crave.repo.RandomRecipeRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

import org.junit.Assert.*

class HomeViewModelTest {


    lateinit var homeViewModel: HomeViewModel

    @RelaxedMockK
    lateinit var randomRecipeRepo: RandomRecipeRepo

    private val testDispatcher = TestCoroutineDispatcher()

    private val expectedData = Result.Success(
        listOf(
            Recipe(
                id = 1,
                title = "First Recipe"
            ),
            Recipe(
                id = 2,
                title = "Second Recipe"
            )
        )
    )


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
        assertFalse(homeViewModel.errorFlow.value)

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
    fun `When randomRecipeRepo returns data then it is sent through the recipeFlow`() = runBlockingTest {
        coEvery {
            randomRecipeRepo.getRecipesFlow(any())
        } coAnswers {
            flow {
                emit(
                    expectedData
                )
            }
        }

        assertTrue(homeViewModel.recipeFlow.value.isEmpty())

        homeViewModel.getRandomRecipes()

        assertEquals(homeViewModel.recipeFlow.value, expectedData.data)
    }

    @Test
    fun `When getRecipesFlow is called then the showLoaderFlow is true`() = runBlockingTest {
        coEvery {
            randomRecipeRepo.getRecipesFlow(any())
        } coAnswers {
            flow {
                emit(Result.InProgress)
            }
        }

        assertFalse(homeViewModel.loaderStatusFlow.value)

        homeViewModel.getRandomRecipes()

        assertTrue(homeViewModel.loaderStatusFlow.value)
    }

    @Test
    fun `When hideError is called then the errorFlow is false`() = runBlockingTest {
        coEvery {
            randomRecipeRepo.getRecipesFlow(any())
        } coAnswers {
            flow {
                emit(Result.Error(Exception("Test Exception")))
            }
        }
        assertFalse(homeViewModel.errorFlow.value)

        homeViewModel.getRandomRecipes()

        assertTrue(homeViewModel.errorFlow.value)

        homeViewModel.hideError()

        assertFalse(homeViewModel.errorFlow.value)
    }
}