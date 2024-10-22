package com.example.diffutilsrecyclerview

import com.example.diffutilsrecyclerview.model.JsonResponse
import retrofit2.Call
import retrofit2.http.GET

interface APInterface {

    @GET("users")
    suspend fun getData(): JsonResponse

}