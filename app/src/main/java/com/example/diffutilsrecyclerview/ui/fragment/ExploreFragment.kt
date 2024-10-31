package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diffutilsrecyclerview.databinding.FragmentExploreBinding
import com.example.diffutilsrecyclerview.ui.adapters.UnsplashAdapter
import com.example.diffutilsrecyclerview.ui.viewmodels.UnsplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    @Inject
    lateinit var adapter: UnsplashAdapter
    private val viewModel: UnsplashViewModel by viewModels()

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSetup()
        viewModel.getUnsplashImagesData(1, 30)
        searchUnsplashImages()
        observeUnsplashSearchImages()
        observeUnsplashImages()
    }

    private fun observeUnsplashSearchImages() {
        viewModel.unsplashSearchData.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                adapter.updateImages(it.results)
            }
        })
    }

    private fun observeUnsplashImages() {
        viewModel.unsplashImagesData.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                adapter.updateImages(it)
            }
        })
    }

    private fun searchUnsplashImages() {
        binding.etSearchImages.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) viewModel.getUnsplashSearchData(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) viewModel.getUnsplashImagesData(1, 30)
                return false
            }
        })
    }

    private fun rvSetup() {
        binding.rvExploreImages.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvExploreImages.setHasFixedSize(true)
        binding.rvExploreImages.adapter = adapter
    }
}