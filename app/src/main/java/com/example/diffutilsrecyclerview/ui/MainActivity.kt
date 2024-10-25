package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.data.models.localDataModels.users
import com.example.diffutilsrecyclerview.ui.viewmodels.UserViewModel
import com.example.diffutilsrecyclerview.databinding.ActivityMainBinding
import com.example.diffutilsrecyclerview.ui.adapters.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}