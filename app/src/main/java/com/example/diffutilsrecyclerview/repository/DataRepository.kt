package com.example.diffutilsrecyclerview.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalUser
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.common.AppDatabase
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteExploreModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModelTwo
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteUsersModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.localRecipes
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.localUsers
import com.example.diffutilsrecyclerview.di.ApiOne
import com.example.diffutilsrecyclerview.di.ApiThree
import com.example.diffutilsrecyclerview.di.ApiTwo
import com.example.diffutilsrecyclerview.util.API_KEY_UNSPLASH
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class DataRepository @Inject constructor(
    @ApiOne private val apiServiceOne: ApiService,
    @ApiTwo private val apiServiceTwo: ApiService,
    @ApiThree private val apiServiceThree: ApiService,
    private val appDatabase: AppDatabase
) {

    private val userDao = appDatabase.userDao()
    private val recipeDao = appDatabase.recipeDao()

    private val _userData = MutableLiveData<RemoteUsersModel?>()
    val userData: LiveData<RemoteUsersModel?> get() = _userData

    private val _recipeData = MutableLiveData<RemoteRecipeModel?>()
    val recipeData: LiveData<RemoteRecipeModel?> get() = _recipeData

    private val _topRecipeData = MutableLiveData<RemoteRecipeModelTwo?>()
    val topRecipeData: LiveData<RemoteRecipeModelTwo?> get() = _topRecipeData

    private val _unsplashImagesData = MutableLiveData<List<UnsplashPhoto?>>()
    val unsplashImagesData: LiveData<List<UnsplashPhoto?>> get() = _unsplashImagesData

    private val _unsplashSearchData = MutableLiveData<RemoteExploreModel?>()
    val unsplashSearchData: LiveData<RemoteExploreModel?> get() = _unsplashSearchData

    suspend fun fetchUserData() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiServiceOne.getUsers()
                response.let {
                    val localUsers = it.users.localUsers()
                    userDao.deleteAll()
                    userDao.insertAll(localUsers)
                    _userData.postValue(response)
                }
            } catch (e: Exception) {
                _userData.postValue(null)
            }
        }
    }

    suspend fun fetchRecipesData() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiServiceOne.getRecipes()
                response.let {
                    val localRecipes = it.recipes.localRecipes()
                    recipeDao.deleteAllRecipes()
                    recipeDao.insertAllRecipes(localRecipes)
                    _recipeData.postValue(response)

                    val count = recipeDao.getRecipesCount()
                    Log.d("DataRepository", "Inserted recipes count: $count")
                }
            } catch (e: Exception) {
                _recipeData.postValue(null)
                Log.e("DataRepository", "Error fetching recipes: ${e.message}")
            }
        }
    }

    suspend fun fetchUnsplashData(page: Int, per_page: Int) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiServiceThree.getUnsplashImages(API_KEY_UNSPLASH, page, per_page)
                response.let {
                    _unsplashImagesData.postValue(response)
                }
            } catch (e: Exception) {
                _unsplashImagesData.postValue(emptyList())
                Log.e("DataRepository", "Error fetching unsplash images: ${e.message}")
            }
        }
    }

    suspend fun fetchUnsplashSearchData(query: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiServiceThree.getUnsplashSearchImages(API_KEY_UNSPLASH, query)
                _unsplashSearchData.postValue(response)
            } catch (e: Exception) {
                _unsplashSearchData.postValue(null)
                Log.e("DataRepository", "Error fetching unsplash search images: ${e.message}")
            }
        }
    }

    suspend fun fetchTopRecipeData() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiServiceTwo.getTopRecipes()
                _topRecipeData.postValue(response)
            } catch (e: Exception) {
                _topRecipeData.postValue(null)
            }
        }
    }

    fun fetchUsersFromRoom(): LiveData<List<LocalUser>> {
        return userDao.getAllUsers()
    }

    fun fetchRecipesFromRoom(): LiveData<List<LocalRecipeModel>> {
        return recipeDao.getAllRecipes()
    }
}