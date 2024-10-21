package com.example.diffutilsrecyclerview.model

data class JsonResponse(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)