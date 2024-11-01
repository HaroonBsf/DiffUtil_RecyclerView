package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diffutilsrecyclerview.databinding.FragmentExploreBinding
import com.example.diffutilsrecyclerview.ui.adapters.UnsplashPagingAdapter
import com.example.diffutilsrecyclerview.ui.viewmodels.UnsplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val viewModel: UnsplashViewModel by viewModels()
    @Inject lateinit var adapter: UnsplashPagingAdapter

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSetup()
        setupSearchView()
        observeUnsplashSearchImages()
        observeUnsplashImages()
    }

    private fun setupSearchView() {
        binding.etSearchImages.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()) {
                        viewModel.searchUnsplashImages(it)
                        adapter.refresh()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) viewModel.searchUnsplashImages("")
                return false
            }
        })
    }

    private fun observeUnsplashImages() {
        viewModel.getUnsplashImages().observe(viewLifecycleOwner, Observer { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        })
    }

    private fun observeUnsplashSearchImages() {
        viewModel.searchResults.observe(viewLifecycleOwner, Observer { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        })
    }

    private fun rvSetup() {
        binding.rvExploreImages.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvExploreImages.setHasFixedSize(true)
        binding.rvExploreImages.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}