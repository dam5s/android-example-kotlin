package com.example.activity

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

import org.junit.Assert.assertTrue
import kotlin.test.assertFalse

RunWith(javaClass<RobolectricTestRunner>())
Config(manifest = "../application/src/main/AndroidManifest.xml", emulateSdk = 18)
public class DeckardActivityTest {

    Test
    throws(javaClass<Exception>())
    public fun testSomething() {
        assertTrue(Robolectric.buildActivity<DeckardActivity>(javaClass<DeckardActivity>()).create().get() != null)
    }
}
