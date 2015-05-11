package com.example

import com.example.api.ApiGateway
import com.example.api.HttpApiGateway

object ServiceLocator {
    val apiGateway: ApiGateway = HttpApiGateway()

}
