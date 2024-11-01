package com.example.diffutilsrecyclerview.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.UnsplashPhoto

@Dao
interface UnsplashDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<UnsplashPhoto>)

    @Query("SELECT * FROM Unsplash_Image")
    fun getImages(): PagingSource<Int, UnsplashPhoto>

    @Query("DELETE FROM Unsplash_Image")
    suspend fun deleteImages()
}