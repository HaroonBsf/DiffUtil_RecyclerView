package com.example.diffutilsrecyclerview.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashRemoteKeys

@Dao
interface UnsplashRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("SELECT * FROM UnsplashRemoteKeys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys?

    @Query("DELETE FROM UnsplashRemoteKeys")
    suspend fun deleteRemoteKeys()
}