package com.example.diffutilsrecyclerview.common

import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteUsersModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): RemoteUsersModel

    @GET("recipes")
    suspend fun getRecipes(): RemoteRecipeModel
}