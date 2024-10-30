package com.example.diffutilsrecyclerview.common

import com.example.diffutilsrecyclerview.data.models.remoteDataModels.ImageModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteExploreModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModelTwo
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteUsersModel
import com.example.diffutilsrecyclerview.util.API_KEY_UNSPLASH
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(): RemoteUsersModel

    @GET("recipes")
    suspend fun getRecipes(): RemoteRecipeModel

    @GET("recipes/list")
    suspend fun getTopRecipes(): RemoteRecipeModelTwo

    @GET("photos")
    fun getUnsplashImages(
        @Query("client_id") client_id: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Call<ImageModel>
}