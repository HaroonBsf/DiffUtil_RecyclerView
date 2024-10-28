package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.diffutilsrecyclerview.databinding.FragmentRecipesBinding
import com.example.diffutilsrecyclerview.ui.adapters.RecipesAdapter
import com.example.diffutilsrecyclerview.ui.viewmodels.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    @Inject
    lateinit var adapter: RecipesAdapter
    private val viewModel: RecipeViewModel by viewModels()

    var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterRecipes()
        recyclerViewSetup()
        observeRemoteRecipes()
        viewModel.getRecipeData()
    }

    private fun filterRecipes() {
        binding.etSearchRecipe.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterRecipesNow(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterRecipesNow(query: String) {
        val filteredRecipes = viewModel.recipeData.value?.recipes?.filter {
            it.name.contains(query, ignoreCase = true)
        } ?: emptyList()
        adapter.updateRecipes(filteredRecipes)
    }

    private fun observeRemoteRecipes() {
        viewModel.recipeData.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                adapter.updateRecipes(it.recipes)
            }
        })
    }

    private fun recyclerViewSetup() {
        binding.apply {
            rvRecipe.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvRecipe.setHasFixedSize(true)
            rvRecipe.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}