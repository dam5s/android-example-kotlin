package com.example

import android.app.Activity
import android.app.Application
import com.example.api.ApiGateway
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment

class TestExampleApplication : ExampleApplication() {
    private var mApplicationModule: ApplicationModule? = null

    override var applicationModule: ApplicationModule
        get() {
            if (mApplicationModule == null) {
                return super.applicationModule
            }
            return mApplicationModule!!
        }
        set(mApplicationModule) {
            this.mApplicationModule = mApplicationModule
            onInit()
        }
}

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
        val app = RuntimeEnvironment.application as TestExampleApplication
        app.applicationModule = TestApplicationModule(app, this)

        return Robolectric.setupActivity(javaClass)
    }
}