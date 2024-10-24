package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.ui.adapters.Adapter
import com.example.diffutilsrecyclerview.ui.viewmodels.UserViewModel
import com.example.diffutilsrecyclerview.databinding.ActivityMainBinding
import com.example.diffutilsrecyclerview.data.models.users
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var adapter: Adapter
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerViewSetup()
        observeRemoteUsers()
        viewModel.getUserData()
    }

    private fun recyclerViewSetup() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
        }
    }

    private fun observeRemoteUsers() {
        viewModel.userData.observe(this, Observer { response ->
            if (response != null){
                adapter.updateUsers(response.users)
            } else{
               observeLocalUsers()
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