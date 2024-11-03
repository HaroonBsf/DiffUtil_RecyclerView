package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.paging.ExperimentalPagingApi
import com.example.diffutilsrecyclerview.BaseActivity
import com.example.diffutilsrecyclerview.databinding.ActivityMainBinding
import com.example.diffutilsrecyclerview.ui.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalPagingApi
class MainActivity : BaseActivity() {

    @Inject
    lateinit var adapter: PagerAdapter

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewPager.adapter = adapter
        tabLayoutMediator()
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
}