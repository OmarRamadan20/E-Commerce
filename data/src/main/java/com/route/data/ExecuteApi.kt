package com.route.data

import com.google.gson.Gson
import com.route.data.api.model.CategoryResponse
import com.route.domain.common.InternetConnectionError
import com.route.domain.common.MyResult
import com.route.domain.common.ServerError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> executeApi(action: suspend () -> T): T {

    return try {
        val response = action.invoke()
        return response
    } catch (e: HttpException) {
        if (e.code() in 400..600) {
            val serverResponse = e.response()?.errorBody()?.string()
            val response = Gson().fromJson(serverResponse, CategoryResponse::class.java)
            throw ServerError(response.message, response.statusMsg)
        }
        else{
            throw e
        }
    }
    catch (ex:IOException){
        throw InternetConnectionError(ex)
    }
    catch (ex:Exception){
        throw ex
    }

}
suspend fun<T> toFlow(getData :suspend ()->T): Flow<MyResult<T>>{
    return flow {
            emit(MyResult.Loading)
            val response = getData.invoke()
            emit(MyResult.Success(response))

    }.catch () {ex->
            when(ex){
                is ServerError ->{
                    emit(MyResult.ServerFail(ex))
                }
                is InternetConnectionError ->{
                    emit(MyResult.Failure(ex))
                }
                is Exception ->{
                    emit(MyResult.Failure(ex))
                }
            }
    }
}