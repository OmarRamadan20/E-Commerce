package com.route.data.datasource

import com.route.data.api.WebServices
import com.route.data.contract.AuthDataSource
import com.route.data.executeApi
import com.route.domain.models.LoginRequest
import com.route.domain.models.LoginResponse
import com.route.domain.models.RegisterRequest
import com.route.domain.models.RegisterRespone
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val webServices: WebServices):AuthDataSource {
    override suspend fun signUp(request: RegisterRequest): RegisterRespone {
        return webServices.signUp(request)
    }

    override suspend fun signIn(request: LoginRequest): LoginResponse {
        return webServices.login(request)
    }

}