package com.example.testmeli.repositories

import com.example.testmeli.models.Results
import com.example.testmeli.repositories.remote.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.testmeli.utils.Result

class ProductRepository(private val remoteDataSource: ProductService) {
    suspend fun getProducts(param:String): Results = withContext(Dispatchers.IO)
    {
        val result = remoteDataSource.getProducts(param)

        when (result) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
        }
    }
}