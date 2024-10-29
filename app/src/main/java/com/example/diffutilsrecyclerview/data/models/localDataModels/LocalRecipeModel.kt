package com.example.diffutilsrecyclerview.data.models.localDataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.Recipe

@Entity(tableName = "recipe_table")
data class LocalRecipeModel(
    @PrimaryKey val id: Int,
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val name: String,
    val prepTimeMinutes: Int,
    val rating: Double,
    val servings: Int
)
fun List<LocalRecipeModel>.recipes(): List<Recipe> {
    return this.map { localRecipe ->
        Recipe(
            id = localRecipe.id,
            caloriesPerServing = localRecipe.caloriesPerServing,
            cookTimeMinutes = localRecipe.cookTimeMinutes,
            cuisine = localRecipe.cuisine,
            image = localRecipe.image,
            difficulty = localRecipe.difficulty,
            ingredients = localRecipe.ingredients,
            instructions = localRecipe.instructions,
            name = localRecipe.name,
            prepTimeMinutes = localRecipe.prepTimeMinutes,
            rating = localRecipe.rating,
            mealType = emptyList(),
            reviewCount = 0,
            tags = emptyList(),
            userId = 0,
            servings = localRecipe.servings
        )
    }
}