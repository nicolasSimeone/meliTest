package com.example.testmeli.repositories.remote

import com.example.testmeli.models.Results
import com.example.testmeli.utils.Result

class ProductService(private val api: ApiClient) {

    suspend fun getProducts(param:String): Result<Results> {

        val response = api.getProductsList(param).await()
        val body = response.body()
        body?.let {
            return Result.Success(body)
        } ?: run {
            val messageError = when(response.code()){
                400 -> "bad request"
                502 -> "bad gateway"
                else -> "unexpected_error"
            }
            return Result.Error(
                Exception(messageError)
            )
        }
    }

}