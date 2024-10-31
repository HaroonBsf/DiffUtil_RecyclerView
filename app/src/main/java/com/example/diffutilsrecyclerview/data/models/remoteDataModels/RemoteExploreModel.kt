package com.example.diffutilsrecyclerview.data.models.remoteDataModels

class RemoteExploreModel(
    val results: List<UnsplashPhoto>
)

data class UnsplashPhoto(
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