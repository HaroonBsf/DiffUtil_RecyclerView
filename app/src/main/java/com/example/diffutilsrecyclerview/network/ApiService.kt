package com.example.diffutilsrecyclerview.network

import com.example.diffutilsrecyclerview.data.models.JsonResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getData(): JsonResponse
}