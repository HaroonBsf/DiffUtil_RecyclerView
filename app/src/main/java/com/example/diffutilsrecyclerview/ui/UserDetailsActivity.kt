package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diffutilsrecyclerview.databinding.ActivityDetailedBinding
import com.example.diffutilsrecyclerview.util.userData

class UserDetailsActivity : AppCompatActivity() {

    val binding: ActivityDetailedBinding by lazy {
        ActivityDetailedBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = userData
        binding.userDetails = data
        binding.back.setOnClickListener { finish() }
        binding.fbAddRecipe.setOnClickListener { Toast.makeText(this, "Adding Data...", Toast.LENGTH_LONG).show() }
    }
}