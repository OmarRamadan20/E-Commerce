package com.route.domain.usecase

import com.route.domain.common.MyResult
import com.route.domain.contract.AuthRepository
import com.route.domain.models.LoginRequest
import com.route.domain.models.LoginResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor(val authRepository: AuthRepository) {
    suspend fun invoke (request: LoginRequest): MyResult<LoginResponse> {
        return authRepository.signIn(request.email,request.password)
    }
}