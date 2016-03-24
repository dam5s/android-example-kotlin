package com.example.login

import com.example.api.ApiMethod
import com.example.api.ApiRequest

class LoginApiRequest(val login: String, val password: String): ApiRequest<LoginApiResponse> {
    override val method = ApiMethod.POST

    override val body: String? get() {
        return "$login $password"
    }

    override fun buildResponse(): LoginApiResponse {
        return LoginApiResponse()
    }
}
