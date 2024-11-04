package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.databinding.FragmentRecipeDetailBinding
import com.example.diffutilsrecyclerview.ui.adapters.IngredientsAdapter
import com.example.diffutilsrecyclerview.ui.adapters.InstructionsAdapter
import com.example.diffutilsrecyclerview.util.recipeData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private var _binding : FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var ingredientsAdapter: IngredientsAdapter
    @Inject
    lateinit var instructionsAdapter: InstructionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(layoutInflater, container, false)

        setup()
        val recipes = recipeData
        binding.recipeDetails = recipes

        recipes?.apply {
            ingredients.let { ingredientsAdapter.updateIngredients(it) }
            instructions.let { instructionsAdapter.updateIngredients(it) }
        }
        return binding.root
    }

    private fun setup() {
        binding.apply {
            rvIngredients.apply { layoutManager = LinearLayoutManager(context)
                adapter = ingredientsAdapter }
            rvInstructions.apply { layoutManager = LinearLayoutManager(context)
                adapter = instructionsAdapter }
//            ivBack.setOnClickListener { finish() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}