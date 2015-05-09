package com.example.activity

import com.example.R
import android.test.ActivityInstrumentationTestCase2
import android.test.suitebuilder.annotation.LargeTest
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

LargeTest
public class DeckardEspressoTest : ActivityInstrumentationTestCase2<DeckardActivity>(javaClass<DeckardActivity>()) {

    throws(javaClass<Exception>())
    override fun setUp() {
        super.setUp()
        getActivity()
    }

    throws(javaClass<InterruptedException>())
    public fun testActivityShouldHaveText() {
        onView(withId(R.id.text)).check(matches(withText("Hello Espresso!")))
    }
}
