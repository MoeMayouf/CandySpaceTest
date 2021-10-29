package com.mayouf.candyspace.utils

import androidx.test.core.app.ActivityScenario.launch
import com.mayouf.candyspace.MainActivity
import com.mayouf.candyspace.webmock.injectTestConfiguration
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TestConfigurationRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        injectTestConfiguration {}
        launch(MainActivity::class.java)
    }
}