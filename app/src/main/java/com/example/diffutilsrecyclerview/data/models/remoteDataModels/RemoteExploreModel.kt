package com.example.diffutilsrecyclerview.data.models.remoteDataModels

class RemoteExploreModel(
    val results: List<UnsplashPhoto>,
    val total_pages: Int
)

data class UnsplashPhoto(
    val id: String,
    val urls: UnsplashPhotoUrls
)

data class UnsplashPhotoUrls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val small_s3: String,
    val thumb: String
)