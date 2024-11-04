package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.paging.ExperimentalPagingApi
import com.example.diffutilsrecyclerview.BaseActivity
import com.example.diffutilsrecyclerview.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPagingApi
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}