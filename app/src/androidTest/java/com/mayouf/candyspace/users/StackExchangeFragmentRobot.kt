package com.mayouf.candyspace.users

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mayouf.candyspace.R
import com.mayouf.candyspace.utils.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers

fun stackExchangeFragmentRobot(func: StackExchangeFragmentRobot.() -> Unit) =
    StackExchangeFragmentRobot().apply { func() }

class StackExchangeFragmentRobot {


    fun waitForCondition(idlingResource: IdlingResource?) = apply {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    fun assertEditSearchIsDisplayed() = apply {
        Espresso.onView(editSearchViewMatcher).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    fun assertProgressBarIsDisplayed() = apply {
        Espresso.onView(animationViewMatcher).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    fun assertRecyclerViewIsNotDisplayed() = apply {
        Espresso.onView(recyclerViewMatcher)
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    fun assertRecyclerViewIsDisplayed() = apply {
        Espresso.onView(recyclerViewMatcher)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun assertItemsSize() = apply {
        Espresso.onView(recyclerViewMatcher).check(
            RecyclerViewItemCountAssertion(20)
        )
    }

    fun assertFilterButtonIsDisplayed() = apply {
        Espresso.onView(searchButtonViewMatcher).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    fun clickUsersAction() = apply {
        Espresso.onView(editSearchViewMatcher).perform(ViewActions.typeText("mohammed"))
        Espresso.onView(searchButtonViewMatcher).perform(ViewActions.click())

    }

    fun assertBodyErrorDisplayed() = apply {
        Espresso.onView(errorViewMatcher)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }



    companion object {

        private val recyclerViewMatcher = withId(R.id.users_recycler_view)

        private val editSearchViewMatcher = withId(R.id.etSearchBox)

        private val animationViewMatcher = withId(R.id.progressBar)

        private val searchButtonViewMatcher = withId(R.id.btnSearch)

        private val errorViewMatcher = withId(R.id.users_error_description)
    }
}