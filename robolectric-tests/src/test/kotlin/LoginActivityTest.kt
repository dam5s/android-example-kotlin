package com.example.activity

import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.R
import com.example.api
import com.example.api.ApiGateway
import com.example.api.ApiMethod
import com.example.api.ApiRequest
import com.example.api.ApiResponse
import com.example.login.LoginActivity
import com.example.tasks.Promise
import login.LoginApiResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

import org.junit.Assert.assertThat
import org.hamcrest.Matchers.*

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

RunWith(javaClass<RobolectricTestRunner>())
Config(manifest = "../application/src/main/AndroidManifest.xml", emulateSdk = 18)
public class LoginActivityTest {

    Test
    public fun testLogin() {
        val activity = Robolectric.setupActivity(javaClass<LoginActivity>())
        val fakeApiGateway = FakeApiGateway()

        activity.apiGateway = fakeApiGateway
        fakeApiGateway.stubbedPromise = Promise<LoginApiResponse>()

        activity.loginEditText.setText("hello@example.com")
        activity.passwordEditText.setText("secret1")
        activity.loginButton.performClick()

        assertThat(fakeApiGateway.performedRequest?.method, equalTo(ApiMethod.POST))
        assertNotNull(fakeApiGateway.performedRequest?.body)
    }
}

class FakeApiGateway: ApiGateway {
    var performedRequest: ApiRequest<*>? = null
    var stubbedPromise: Promise<*>? = null

    override fun <T : ApiResponse> doRequest(request: ApiRequest<T>): Promise<T> {
        performedRequest = request
        return stubbedPromise as Promise<T>
    }
}
