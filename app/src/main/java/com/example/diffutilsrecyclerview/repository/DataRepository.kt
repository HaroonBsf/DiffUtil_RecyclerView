package com.example.diffutilsrecyclerview.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalUser
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.common.AppDatabase
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteUsersModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.localRecipes
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.localUsers
import com.example.diffutilsrecyclerview.di.ApiOne
import com.example.diffutilsrecyclerview.di.ApiTwo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class DataRepository @Inject constructor(
    @ApiOne private val apiServiceOne: ApiService,
    private val appDatabase: AppDatabase
) {

    private val userDao = appDatabase.userDao()
    private val recipeDao = appDatabase.recipeDao()

    private val _userData = MutableLiveData<RemoteUsersModel?>()
    val userData: LiveData<RemoteUsersModel?> get() = _userData

    private val _recipeData = MutableLiveData<RemoteRecipeModel?>()
    val recipeData: LiveData<RemoteRecipeModel?> get() = _recipeData

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

    fun fetchUsersFromRoom(): LiveData<List<LocalUser>> {
        return userDao.getAllUsers()
    }

    fun fetchRecipesFromRoom(): LiveData<List<LocalRecipeModel>> {
        return recipeDao.getAllRecipes()
    }
}