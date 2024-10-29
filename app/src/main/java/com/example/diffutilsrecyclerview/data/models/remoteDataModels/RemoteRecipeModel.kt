package com.example.diffutilsrecyclerview.data.models.remoteDataModels

import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalRecipeModel

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

fun List<Recipe>.localRecipes(): List<LocalRecipeModel> {
    return this.map { recipe ->
        LocalRecipeModel(
            id = recipe.id,
            caloriesPerServing = recipe.caloriesPerServing,
            cookTimeMinutes = recipe.cookTimeMinutes,
            cuisine = recipe.cuisine,
            image = recipe.image,
            difficulty = recipe.difficulty,
            ingredients = recipe.ingredients,
            instructions = recipe.instructions,
            name = recipe.name,
            prepTimeMinutes = recipe.prepTimeMinutes,
            rating = recipe.rating,
            servings = recipe.servings
        )
    }
}