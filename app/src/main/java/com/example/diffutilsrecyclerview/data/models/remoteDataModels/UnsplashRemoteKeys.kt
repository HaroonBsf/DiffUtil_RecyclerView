package com.example.diffutilsrecyclerview.data.models.remoteDataModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)