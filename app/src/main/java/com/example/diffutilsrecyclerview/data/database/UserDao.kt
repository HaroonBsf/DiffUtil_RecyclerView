package com.example.diffutilsrecyclerview.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalUser

@Dao
interface UserDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<LocalUser>)

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<LocalUser>>

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}