package com.route.domain.contract

import com.route.domain.common.MyResult
import com.route.domain.models.LoginResponse
import com.route.domain.models.RegisterRequest
import com.route.domain.models.RegisterRespone

interface AuthRepository {
    suspend fun signUp(name:String,email:String,password:String): MyResult<RegisterRespone>
    suspend fun signIn(email:String,password:String): MyResult<LoginResponse>

}