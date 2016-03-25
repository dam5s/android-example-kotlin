package com.example.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.ExampleApplication
import com.example.R
import com.example.api.ApiGateway
import kotlinx.android.synthetic.main.login.*
import javax.inject.Inject

class LoginActivity : Activity() {

    @Inject
    lateinit var apiGateway: ApiGateway

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        ExampleApplication.container.inject(this)

        setTitle(R.string.pleaseSignIn)

        loginButton.setOnClickListener {
            val request = LoginApiRequest(login = "FIX ME", password = "FIX ME")
            Log.d("LoginActivity", "Login attempt with ${request.login} ${request.password}")

            apiGateway.doRequest(request)
        }
    }
}
