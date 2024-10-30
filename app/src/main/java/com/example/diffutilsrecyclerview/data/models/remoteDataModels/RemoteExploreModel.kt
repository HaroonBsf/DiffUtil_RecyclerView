package com.example.diffutilsrecyclerview.data.models.remoteDataModels

class RemoteExploreModel : ArrayList<ImageModel>()

data class ImageModel(
    val urls: Urls
)

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val small_s3: String,
    val thumb: String
)