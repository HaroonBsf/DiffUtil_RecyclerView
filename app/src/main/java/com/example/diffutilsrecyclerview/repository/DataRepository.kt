package com.example.diffutilsrecyclerview.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diffutilsrecyclerview.data.database.UserDao
import com.example.diffutilsrecyclerview.data.models.JsonResponse
import com.example.diffutilsrecyclerview.data.models.LocalUser
import com.example.diffutilsrecyclerview.data.models.localUsers
import com.example.diffutilsrecyclerview.common.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

/*class DataRepository @Inject constructor(private val apInterface: ApiService, private val appDatabase: AppDatabase) {
    private val userDao = appDatabase.userDao()...*/

class DataRepository @Inject constructor(private val apInterface: ApiService, private val userDao: UserDao) {
    private val _userData = MutableLiveData<JsonResponse?>()
    val userData: LiveData<JsonResponse?> get() = _userData

    suspend fun fetchUserData() {
        withContext(Dispatchers.IO) {
            try {
                val response = apInterface.getData()
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

    fun fetchUsersFromRoom(): LiveData<List<LocalUser>> {
        return userDao.getAllUsers()
    }
}