package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.databinding.ActivityRecipeDetailsBinding
import com.example.diffutilsrecyclerview.ui.adapters.IngredientsAdapter
import com.example.diffutilsrecyclerview.ui.adapters.InstructionsAdapter
import com.example.diffutilsrecyclerview.util.recipeData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var ingredientsAdapter: IngredientsAdapter
    @Inject
    lateinit var instructionsAdapter: InstructionsAdapter

    val binding: ActivityRecipeDetailsBinding by lazy {
        ActivityRecipeDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setup()
        val recipes = recipeData
        binding.recipeDetails = recipes

        recipes?.apply {
            ingredients.let { ingredientsAdapter.updateIngredients(it) }
            instructions.let { instructionsAdapter.updateIngredients(it) }
        }
    }

    private fun setup() {
        binding.apply {
            rvIngredients.apply { layoutManager = LinearLayoutManager(context)
                adapter = ingredientsAdapter }
            rvInstructions.apply { layoutManager = LinearLayoutManager(context)
                adapter = instructionsAdapter }
            ivBack.setOnClickListener { finish() }
        }
    }
}
