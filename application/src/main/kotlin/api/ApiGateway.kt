package com.example.api

import com.example.tasks.Promise

trait ApiGateway {
    fun <T: ApiResponse> doRequest(request: ApiRequest<T>): Promise<T>
}

trait ApiRequest<T: ApiResponse> {
    val method: ApiMethod
    val body: Any?

    fun buildResponsePromise(): Promise<T> {
        return Promise()
    }

    fun buildResponse(): T
}

trait ApiResponse {
}

enum class ApiMethod {
    GET
    POST
    PUT
    DELETE
}
