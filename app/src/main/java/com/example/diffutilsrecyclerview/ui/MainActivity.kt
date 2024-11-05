package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.paging.ExperimentalPagingApi
import com.example.diffutilsrecyclerview.BaseActivity
import com.example.diffutilsrecyclerview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPagingApi
class MainActivity : BaseActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}