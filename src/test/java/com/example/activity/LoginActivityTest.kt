package com.example.activity

import com.example.BuildConfig
import com.example.api.ApiGateway
import com.example.api.ApiMethod
import com.example.api.ApiRequest
import com.example.api.ApiResponse
import com.example.login.LoginActivity
import com.example.Promise
import com.example.login.LoginApiResponse
import kotlinx.android.synthetic.main.login.*
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class LoginActivityTest {

    @Test
    fun testLogin() {
        val activity = Robolectric.setupActivity(LoginActivity().javaClass)
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

class FakeApiGateway : ApiGateway {
    var performedRequest: ApiRequest<*>? = null
    var stubbedPromise: Promise<*>? = null

    override fun <T : ApiResponse> doRequest(request: ApiRequest<T>): Promise<T> {
        performedRequest = request
        return stubbedPromise as Promise<T>
    }
}
