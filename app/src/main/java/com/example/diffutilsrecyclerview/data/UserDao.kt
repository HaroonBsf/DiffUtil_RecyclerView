package com.example.diffutilsrecyclerview.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diffutilsrecyclerview.model.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<LocalUser>)

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): Flow<List<LocalUser>>

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

}