package com.example.testmeli.repositories.remote

import com.example.testmeli.models.Results
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("/sites/MLA/search?category=MLA5725")
    fun getProductsList(): Deferred<Response<Results>>
}