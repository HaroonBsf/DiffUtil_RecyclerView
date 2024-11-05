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
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.diffutilsrecyclerview.data.models.localDataModels.recipes
import com.example.diffutilsrecyclerview.databinding.FragmentRecipesBinding
import com.example.diffutilsrecyclerview.ui.adapters.RecipeAdapter
import com.example.diffutilsrecyclerview.ui.adapters.TopRecipeAdapter
import com.example.diffutilsrecyclerview.ui.viewmodels.RecipeViewModel
import com.example.diffutilsrecyclerview.ui.viewmodels.TopRecipeViewModel
import com.facebook.shimmer.Shimmer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalPagingApi
class RecipesFragment : Fragment() {

    @Inject lateinit var adapter: RecipeAdapter
    @Inject
    lateinit var topAdapter: TopRecipeAdapter
    private val viewModel: RecipeViewModel by viewModels()
    private val topViewModel: TopRecipeViewModel by viewModels()

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
        topViewModel.getTopRecipeData()
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
        adapter.updateUsers(filteredRecipes)
    }

    private fun observeRemoteRecipes() {
        observeTopRecipes()
        viewModel.recipeData.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                adapter.updateUsers(response.recipes)
            } else {
                observeLocalRecipes()
            }
        })
    }

    private fun observeTopRecipes() {
        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setBaseAlpha(0.3f)
            .setHighlightAlpha(0.1f)
            .setDuration(2000)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setTilt(10f)
            .build()

        binding.shimmerLayout.setShimmer(shimmer)
        binding.shimmerLayout.startShimmer()
        topViewModel.topRecipeData.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                topAdapter.updateTopRecipes(it.results)
                binding.rvHorizontalRecipe.visibility = View.VISIBLE
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            }
        })
    }

    private fun observeLocalRecipes() {
        viewModel.localRecipesData.observe(viewLifecycleOwner, Observer { localRecipes ->
            localRecipes?.let {
                adapter.updateUsers(it.recipes())
            }
        })
    }

    private fun recyclerViewSetup() {
        binding.apply {
            rvRecipe.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvRecipe.setHasFixedSize(true)
            rvRecipe.adapter = adapter

            rvHorizontalRecipe.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvHorizontalRecipe.setHasFixedSize(true)
            rvHorizontalRecipe.adapter = topAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}