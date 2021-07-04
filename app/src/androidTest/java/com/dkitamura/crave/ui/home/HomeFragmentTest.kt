package com.dkitamura.crave.ui.home

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.dkitamura.crave.FakeRandomRecipeRepoImpl
import com.dkitamura.crave.MainActivity
import com.dkitamura.crave.launchFragmentInHiltContainer
import com.dkitamura.crave.repo.RandomRecipeRepo
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeFragmentTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun test() {
        launchFragmentInHiltContainer<HomeFragment>()

        onView(withText("Test Recipe")).check(matches(withText("Test Recipe")))

    }
}