package com.example.diffutilsrecyclerview.data.models.remoteDataModels

import androidx.room.Entity
import androidx.room.PrimaryKey

class RemoteExploreModel(
    val results: List<UnsplashPhoto>,
    val total_pages: Int
)

@Entity(tableName = "Unsplash_Image")
data class UnsplashPhoto(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val urls: UnsplashPhotoUrls
)

data class UnsplashPhotoUrls(
    val regular: String
)