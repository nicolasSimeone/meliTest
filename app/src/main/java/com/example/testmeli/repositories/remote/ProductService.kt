package com.example.testmeli.repositories.remote

import com.example.testmeli.models.Results
import com.example.testmeli.utils.Result

class ProductService(private val api: ApiClient) {

    suspend fun getProducts(): Result<Results> {

        val response = api.getProductsList().await()
        val body = response.body()
        body?.let {
            return Result.Success(body)
        } ?: run {
            return Result.Error(
                Exception("error")
            )
        }
    }

}