package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diffutilsrecyclerview.util.Constant
import com.example.diffutilsrecyclerview.databinding.ActivityDetailedBinding

class UserDetailsActivity : AppCompatActivity() {

    val binding: ActivityDetailedBinding by lazy {
        ActivityDetailedBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = Constant.userData
        binding.userDetails = data
        binding.back.setOnClickListener { finish() }
    }
}