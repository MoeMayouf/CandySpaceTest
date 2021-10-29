package com.mayouf.candyspace.users

import android.view.View
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mayouf.candyspace.MainActivity
import com.mayouf.candyspace.R
import com.mayouf.candyspace.utils.TestConfigurationRule
import com.mayouf.candyspace.utils.ViewVisibilityIdlingResource
import com.mayouf.candyspace.webmock.ErrorDispatcher
import com.mayouf.candyspace.webmock.SuccessDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StackExchangeFragmentTest {
    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @get:Rule
    val espressoRule = TestConfigurationRule()

    private val mockWebServer = MockWebServer()

    private var progressBarGoneIdlingResource: ViewVisibilityIdlingResource? = null

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @Test
    fun elementsVisibilityAfterOpeningTheScreen() {
        mockWebServer.dispatcher = SuccessDispatcher()

        progressBarGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.progressBar),
                View.GONE
            )

        launchesFragmentRobot {
            clickLaunchesTab()
            assertEditSearchIsDisplayed()
            waitForCondition(progressBarGoneIdlingResource)
            assertRecyclerViewIsDisplayed()
        }
    }

    @Test
    fun itemsListed() {
        mockWebServer.dispatcher = SuccessDispatcher()

        progressBarGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.progressBar),
                View.GONE
            )

        launchesFragmentRobot {
            clickLaunchesTab()
            waitForCondition(progressBarGoneIdlingResource)
            assertRecyclerViewIsDisplayed()
            assertItemsSize()
        }
    }

    @Test
    fun displayBodyError() {
        mockWebServer.dispatcher = ErrorDispatcher()

        progressBarGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.progressBar),
                View.GONE
            )

        launchesFragmentRobot {
            clickLaunchesTab()
            waitForCondition(progressBarGoneIdlingResource)
            assertBodyErrorDisplayed()
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(progressBarGoneIdlingResource)
    }
}