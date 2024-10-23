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
import com.example.diffutilsrecyclerview.adapters.Adapter
import com.example.diffutilsrecyclerview.util.RetrofitClient
import com.example.diffutilsrecyclerview.data.AppDatabase
import com.example.diffutilsrecyclerview.data.DataRepository
import com.example.diffutilsrecyclerview.viewmodels.UserViewModel
import com.example.diffutilsrecyclerview.data.UserViewModelFactory
import com.example.diffutilsrecyclerview.databinding.ActivityMainBinding
import com.example.diffutilsrecyclerview.model.users

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
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = Adapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getUserData()
        viewModel.userData.observe(this, Observer { response ->
            response?.let {
                adapter.updateUsers(it.users)
                binding.recyclerView.adapter = adapter
            }
        })

        observeLocalUsers()
    }

    private fun observeLocalUsers() {
        viewModel.localUserData.observe(this, Observer { localUsers ->
            localUsers?.let {
                val userList = it.users()
                adapter.updateUsers(userList)
                binding.recyclerView.adapter = adapter
            }
        })
    }
}