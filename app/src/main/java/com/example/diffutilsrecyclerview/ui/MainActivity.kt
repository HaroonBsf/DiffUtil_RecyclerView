package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.ui.adapters.Adapter
import com.example.diffutilsrecyclerview.network.RetrofitClient
import com.example.diffutilsrecyclerview.data.database.AppDatabase
import com.example.diffutilsrecyclerview.repository.DataRepository
import com.example.diffutilsrecyclerview.ui.viewmodels.UserViewModel
import com.example.diffutilsrecyclerview.ui.viewmodels.UserViewModelFactory
import com.example.diffutilsrecyclerview.databinding.ActivityMainBinding
import com.example.diffutilsrecyclerview.data.models.users

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(
            DataRepository(
                RetrofitClient.getData(), database.userDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = Adapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.getUserData()
        observeRemoteUsers()
        observeLocalUsers()
    }

    private fun observeRemoteUsers() {
        viewModel.userData.observe(this, Observer { response ->
            response?.let {
                adapter.updateUsers(it.users)
            }
        })
    }

    private fun observeLocalUsers() {
        viewModel.localUserData.observe(this, Observer { localUsers ->
            localUsers?.let {
                val userList = it.users()
                adapter.updateUsers(userList)
            }
        })
    }
}