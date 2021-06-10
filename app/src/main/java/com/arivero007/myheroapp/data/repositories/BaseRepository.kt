package com.arivero007.myheroapp.data.repositories

import com.arivero007.myheroapp.model.AuthModel
import com.arivero007.myheroapp.data.network.Resource
import com.arivero007.myheroapp.resources.Constants
import com.arivero007.myheroapp.resources.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO){

            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable){
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> Resource.Failure(true, null, null)
                }
            }
        }
    }

    fun generateHashData(): AuthModel{
        val ts = System.currentTimeMillis()/1000
        val hash = Utils.getMD5(ts.toString() + Constants.apiKeyPri + Constants.apiKeyPu)
        return AuthModel(ts.toString(), hash)
    }
}