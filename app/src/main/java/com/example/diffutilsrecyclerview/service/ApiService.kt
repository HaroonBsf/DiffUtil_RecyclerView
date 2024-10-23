package com.example.diffutilsrecyclerview.service

import com.example.diffutilsrecyclerview.model.JsonResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getData(): JsonResponse
}