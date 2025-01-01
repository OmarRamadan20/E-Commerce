package com.route.data.contract

import com.route.domain.models.LoginRequest
import com.route.domain.models.LoginResponse
import com.route.domain.models.RegisterRequest
import com.route.domain.models.RegisterRespone

interface AuthDataSource  {
    suspend fun signUp(request: RegisterRequest): RegisterRespone
    suspend fun signIn(request: LoginRequest): LoginResponse
}