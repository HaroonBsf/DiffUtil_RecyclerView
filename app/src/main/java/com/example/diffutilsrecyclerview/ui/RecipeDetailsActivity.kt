package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.databinding.ActivityDetailedBinding
import com.example.diffutilsrecyclerview.databinding.ActivityRecipeDetailsBinding

class RecipeDetailsActivity : AppCompatActivity() {

    val binding : ActivityRecipeDetailsBinding by lazy {
        ActivityRecipeDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}