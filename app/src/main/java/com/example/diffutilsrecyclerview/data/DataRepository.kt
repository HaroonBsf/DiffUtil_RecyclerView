package com.example.diffutilsrecyclerview.data

import com.example.diffutilsrecyclerview.APInterface
import com.example.diffutilsrecyclerview.model.Address
import com.example.diffutilsrecyclerview.model.Coordinates
import com.example.diffutilsrecyclerview.model.Hair
import com.example.diffutilsrecyclerview.model.JsonResponse
import com.example.diffutilsrecyclerview.model.LocalUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DataRepository(private val apInterface: APInterface, private val userDao: UserDao) {

    suspend fun fetchUserData(): JsonResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apInterface.getData()
                response?.let {
//                    val localUsers = it.users.localUsers()
                    val localUsers = it.users.map { user ->
                        LocalUser(
                            id = user.id,
                            firstName = user.firstName,
                            lastName = user.lastName,
                            maidenName = user.maidenName,
                            age = user.age,
                            gender = user.gender,
                            phone = user.phone,
                            username = user.username,
                            birthDate = user.birthDate,
                            bloodGroup = user.bloodGroup,
                            eyeColor = user.eyeColor,
                            email = user.email,
                            image = user.image,
                            hair = Hair(user.hair.color, user.hair.type),
                            address = Address(user.address.address, user.address.city, Coordinates(0.0, 0.0), user.address.country, "", user.address.state, "")
                        )
                    }
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