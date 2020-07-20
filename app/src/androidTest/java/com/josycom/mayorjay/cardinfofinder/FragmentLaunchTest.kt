package com.josycom.mayorjay.cardinfofinder

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josycom.mayorjay.cardinfofinder.ui.CardInfoFragment
import com.josycom.mayorjay.cardinfofinder.ui.HomeFragment
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentLaunchTest {

    @Test
    fun displayHomeFragmentWhenLaunched() {
        launchFragmentInContainer<HomeFragment>(null, R.style.AppTheme, null)
        onView(withId(R.id.tv_welcome_text)).check(matches(not(hasFocus())))
        onView(withId(R.id.bt_start)).check(matches(isDisplayed()))
    }

    @Test
    fun displayCardInfoFragmentWhenLaunched() {
        launchFragmentInContainer<CardInfoFragment>(null, R.style.AppTheme, null)
        onView(withText("Please input your Card Number")).check(matches(isDisplayed()))
        onView(withId(R.id.card_number_input_editText)).check(matches(isClickable()))
    }
}