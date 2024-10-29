package com.example.diffutilsrecyclerview.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diffutilsrecyclerview.data.models.localDataModels.LocalRecipeModel

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecipes(recipes: List<LocalRecipeModel>)

    @Query("SELECT * FROM recipe_table")
    fun getAllRecipes(): LiveData<List<LocalRecipeModel>>

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipes()

    @Query("SELECT COUNT(*) FROM recipe_table")
    suspend fun getRecipesCount(): Int
}