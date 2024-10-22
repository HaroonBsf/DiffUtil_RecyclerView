package com.example.diffutilsrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsrecyclerview.data.DataRepository
import com.example.diffutilsrecyclerview.data.ViewModelClass
import com.example.diffutilsrecyclerview.data.ViewModelFactory
import com.example.diffutilsrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: ViewModelClass by viewModels {
        ViewModelFactory(DataRepository(RetrofitClient.getData()))
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
    }
}