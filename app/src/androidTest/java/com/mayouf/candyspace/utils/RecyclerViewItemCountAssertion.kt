package com.mayouf.candyspace.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        noViewFoundException?.let {
            throw  noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter?.itemCount, CoreMatchers.`is`(expectedCount))
    }

}