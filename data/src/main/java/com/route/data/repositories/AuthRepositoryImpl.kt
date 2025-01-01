package com.route.data.repositories

import com.route.data.contract.AuthDataSource
import com.route.domain.common.MyResult
import com.route.domain.contract.AuthRepository
import com.route.domain.models.LoginRequest
import com.route.domain.models.LoginResponse
import com.route.domain.models.RegisterRequest
import com.route.domain.models.RegisterRespone
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthDataSource) :AuthRepository {
    override suspend fun signUp(name:String,email:String , password:String): MyResult<RegisterRespone> {
        return try {
            val request = RegisterRequest(name,email, password)
            val response = dataSource.signUp(request)
            MyResult.Success(response)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }

    override suspend fun signIn(email: String, password: String): MyResult<LoginResponse> {

        return try {
            val request = LoginRequest(email, password)
            val response = dataSource.signIn(request)
            MyResult.Success(response)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }
}