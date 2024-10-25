package com.example.diffutilsrecyclerview.data.models.remoteDataModels

data class RemoteRecipeModel(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)

data class Recipe(
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    val name: String,
    val prepTimeMinutes: Int,
    val rating: Double,
    val reviewCount: Int,
    val servings: Int,
    val tags: List<String>,
    val userId: Int
)