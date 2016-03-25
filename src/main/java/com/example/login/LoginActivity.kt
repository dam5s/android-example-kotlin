package com.example.login

import android.app.Activity
import android.os.Bundle
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
        ExampleApplication.graph.inject(this)

        setTitle(R.string.pleaseSignIn)

        loginButton.setOnClickListener {
            val request = LoginApiRequest(login = "FIX ME", password = "FIX ME")
            apiGateway.doRequest(request)
        }
    }
}
