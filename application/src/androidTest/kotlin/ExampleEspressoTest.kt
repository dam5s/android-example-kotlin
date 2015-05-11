package com.example.activity

import com.example.R
import android.test.ActivityInstrumentationTestCase2
import android.test.suitebuilder.annotation.LargeTest
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.example.login.LoginActivity

LargeTest
public class ExampleEspressoTest : ActivityInstrumentationTestCase2<LoginActivity>(javaClass<LoginActivity>()) {

    override fun setUp() {
        super.setUp()
        getActivity()
    }

    public fun testActivityShouldHaveText() {
        onView(withId(R.id.loginTextView)).check(matches(withText("Login")))
    }
}
