package com.example.diffutilsrecyclerview

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
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalPagingApi
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.viewPager.adapter = adapter
        tabLayoutMediator()
        return binding.root
    }

    private fun tabLayoutMediator() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Recipes"
                1 -> "Users"
                2 -> "Explore"
                else -> null
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}