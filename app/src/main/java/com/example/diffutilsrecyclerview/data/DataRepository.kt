package com.example.diffutilsrecyclerview.data

import com.example.diffutilsrecyclerview.APInterface
import com.example.diffutilsrecyclerview.model.JsonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DataRepository(private val apInterface: APInterface) {

    suspend fun fetchUserData(): JsonResponse? {
        return withContext(Dispatchers.IO) {
            try {
                apInterface.getData()
            } catch (e: Exception){
                throw e
            }
        }
    }
}