package com.route.domain.usecase

import com.route.domain.common.MyResult
import com.route.domain.contract.AuthRepository
import com.route.domain.models.RegisterRequest
import com.route.domain.models.RegisterRespone
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun invoke (request: RegisterRequest):MyResult<RegisterRespone> {
        return authRepository.signUp(request.name,request.email,request.password)
    }
}