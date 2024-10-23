package com.example.diffutilsrecyclerview.data

import com.example.diffutilsrecyclerview.model.JsonResponse
import com.example.diffutilsrecyclerview.model.LocalUser
import com.example.diffutilsrecyclerview.model.localUsers
import com.example.diffutilsrecyclerview.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DataRepository(private val apInterface: ApiService, private val userDao: UserDao) {

    suspend fun fetchUserData(): JsonResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apInterface.getData()
                response?.let {
                    val localUsers = it.users.localUsers()
                    userDao.deleteAll()
                    userDao.insertAll(localUsers)
                }
                response
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchUsersFromRoom(): List<LocalUser> {
        return withContext(Dispatchers.IO) {
            userDao.getAllUsers()
        }
    }
}