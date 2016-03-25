package com.example

import android.app.Activity
import android.app.Application
import com.example.api.ApiGateway
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment

class TestApplicationModule(
        private val application: Application,
        private val container: testContainer
) : ApplicationModule(application) {

    override fun provideApiGateway() = container.apiGateway
}

data class testContainer(
        val apiGateway: ApiGateway = Mockito.mock(ApiGateway::class.java)
) {

    fun <A: Activity> buildActivity(javaClass: Class<A>): A {
        val app = RuntimeEnvironment.application as ExampleApplication
        app.initializeContainer(TestApplicationModule(app, this))

        return Robolectric.setupActivity(javaClass)
    }
}