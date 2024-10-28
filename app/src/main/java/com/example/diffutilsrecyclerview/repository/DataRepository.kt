package com.example.diffutilsrecyclerview.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diffutilsrecyclerview.data.database.UserDao
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalUser
import com.example.diffutilsrecyclerview.common.ApiService
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteRecipeModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.RemoteUsersModel
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.localUsers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

/*class DataRepository @Inject constructor(private val apInterface: ApiService, private val appDatabase: AppDatabase) {
    private val userDao = appDatabase.userDao()...*/

class DataRepository @Inject constructor(private val apInterface: ApiService, private val userDao: UserDao) {
    private val _userData = MutableLiveData<RemoteUsersModel?>()
    val userData: LiveData<RemoteUsersModel?> get() = _userData

    private val _recipeData = MutableLiveData<RemoteRecipeModel?>()
    val recipeData: LiveData<RemoteRecipeModel?> get() = _recipeData

    suspend fun fetchUserData() {
        withContext(Dispatchers.IO) {
            try {
                val response = apInterface.getUsers()
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
                val response = apInterface.getRecipes()
                    _recipeData.postValue(response)
            } catch (e: Exception) {
                _recipeData.postValue(null)
            }
        }
    }

    fun fetchUsersFromRoom(): LiveData<List<LocalUser>> {
        return userDao.getAllUsers()
    }
}