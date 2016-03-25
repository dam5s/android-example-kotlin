package com.example.activity

import com.example.*
import com.example.api.ApiGateway
import com.example.api.ApiMethod
import com.example.api.ApiRequest
import com.example.api.ApiResponse
import com.example.login.LoginActivity
import com.example.login.LoginApiRequest
import com.example.login.LoginApiResponse
import kotlinx.android.synthetic.main.login.*
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21), application = TestExampleApplication::class)
class LoginActivityTest {

    private val fakeApiGateway = FakeApiGateway()
    private val activity = testContainer(apiGateway = fakeApiGateway).buildActivity(LoginActivity::class.java)

    @Test
    fun testLogin() {
        fakeApiGateway.stubbedPromise = Promise<LoginApiResponse>()

        activity.loginEditText.setText("hello@example.com")
        activity.passwordEditText.setText("secret1")
        activity.loginButton.performClick()

        assertThat(fakeApiGateway.performedRequest?.method, equalTo(ApiMethod.POST))
        assertNotNull(fakeApiGateway.performedRequest?.body)

        val expectedLoginApiRequest = LoginApiRequest("hello@example.com", "secret1")
        assertThat(fakeApiGateway.performedRequest!! as LoginApiRequest, equalTo(expectedLoginApiRequest))
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
