package com.example.api

import com.example.tasks.Promise

class HttpApiGateway: ApiGateway {
    override fun <T : ApiResponse> doRequest(request: ApiRequest<T>): Promise<T> {
        return request.buildResponsePromise()
    }
}
