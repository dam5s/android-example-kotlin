package com.example.api

import com.example.Promise

interface ApiGateway {
    fun <T: ApiResponse> doRequest(request: ApiRequest<T>): Promise<T>
}

interface ApiRequest<T: ApiResponse> {
    val method: ApiMethod
    val body: Any?

    fun buildResponsePromise(): Promise<T> {
        return Promise()
    }

    fun buildResponse(): T
}

interface ApiResponse {
}

enum class ApiMethod {
    GET,
    POST,
    PUT,
    DELETE
}
