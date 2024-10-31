package com.example.diffutilsrecyclerview.common

import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteExploreModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModelTwo
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteUsersModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(): RemoteUsersModel

    @GET("recipes")
    suspend fun getRecipes(): RemoteRecipeModel

    @GET("recipes/list")
    suspend fun getTopRecipes(): RemoteRecipeModelTwo

    @GET("photos")
    suspend fun getUnsplashImages(
        @Query("client_id") client_id: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): List<UnsplashPhoto>

    @GET("search/photos")
    suspend fun getUnsplashSearchImages(
        @Query("client_id") client_id: String,
        @Query("query") query: String
    ): RemoteExploreModel
}