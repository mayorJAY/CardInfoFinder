package com.josycom.mayorjay.cardinfofinder

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josycom.mayorjay.cardinfofinder.ui.HomeFragment
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @Test
    fun whenButtonIsClicked_navigateToCardInfoFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val scenario = launchFragmentInContainer<HomeFragment>(null, R.style.AppTheme, null)
        navController.setGraph(R.navigation.nav_graph)
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        onView(withId(R.id.bt_start)).perform(forceClick())
        assertThat(navController.currentDestination?.id, `is`(R.id.cardInfoFragment))
    }

    @Test
    fun whenNavControllerIsCalled_PerformAccurateNavigation() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val scenario = launchFragmentInContainer<HomeFragment>(null, R.style.AppTheme, null)
        navController.setGraph(R.navigation.nav_graph)
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        navController.navigate(R.id.action_homeFragment_to_cardInfoFragment)
        assertThat(navController.currentDestination?.id, `is`(R.id.cardInfoFragment))
    }

    // Helper function to perform click action on a View
    private fun forceClick(): ViewAction {
        return object: ViewAction{
            override fun getDescription(): String {
                return "force click"
            }

            override fun getConstraints(): Matcher<View> {
                return allOf(isClickable(), isEnabled(), isDisplayed())
            }

            override fun perform(uiController: UiController?, view: View?) {
                view?.performClick()
                uiController?.loopMainThreadUntilIdle()
            }
        }
    }
}