package com.example.diffutilsrecyclerview.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.ExperimentalPagingApi
import com.example.diffutilsrecyclerview.databinding.FragmentHomeBinding
import com.example.diffutilsrecyclerview.ui.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPagingApi
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PagerAdapter(this)
        binding.viewPager.adapter = adapter
        tabLayoutMediator()

    }

    private fun tabLayoutMediator() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Recipes"
                1 -> "Users"
                else -> "Explore"
            }
        }.attach()
    }

    override fun onDestroyView() {
//        binding.viewPager.adapter = null
        _binding = null
        super.onDestroyView()
    }
}