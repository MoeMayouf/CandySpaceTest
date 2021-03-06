package com.mayouf.candyspace.webmock

import android.content.Context
import android.net.Uri
import androidx.test.platform.app.InstrumentationRegistry
import com.mayouf.candyspace.webmock.AssetReaderUtil.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

const val USERS = "/users"
const val USERS_SUCCESS = "users.json"

class SuccessDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
) : Dispatcher() {
    private val responseFilesByPath: Map<String, String> = mapOf(
        USERS to USERS_SUCCESS
    )

    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        val pathWithoutQueryParams = Uri.parse(request.path).path ?: return errorResponse
        val responseFile = responseFilesByPath[pathWithoutQueryParams]
        return if (responseFile != null) {
            val responseBody = asset(context, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        } else {
            errorResponse
        }
    }
}