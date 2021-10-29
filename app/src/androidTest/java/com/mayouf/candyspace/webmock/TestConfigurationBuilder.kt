package com.mayouf.candyspace.webmock

import androidx.test.platform.app.InstrumentationRegistry
import com.mayouf.candyspace.StackExchangeApp
import com.mayouf.candyspace.di.AppComponent
import com.mayouf.candyspace.di.DaggerAppComponent
import com.mayouf.data.di.ApiModule


class TestConfigurationBuilder {
    private val baseUrl: String = "https://127.0.0.1:8080"

    fun inject() {
        appComponent {
            apiModule(ApiModule(baseUrl))
        }.inject(requireTestedApplication())
    }
}

fun injectTestConfiguration(block: TestConfigurationBuilder.() -> Unit) {
    TestConfigurationBuilder().apply(block).inject()
}

private fun appComponent(block: DaggerAppComponent.Builder.() -> Unit = {}): AppComponent =
    DaggerAppComponent.builder().apply(block).build()

private fun requireTestedApplication() =
    (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as StackExchangeApp)