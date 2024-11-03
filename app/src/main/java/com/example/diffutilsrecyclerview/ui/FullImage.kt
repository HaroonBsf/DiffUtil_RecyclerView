package com.example.diffutilsrecyclerview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diffutilsrecyclerview.BaseActivity
import com.example.diffutilsrecyclerview.databinding.ActivityFullImageBinding
import com.example.diffutilsrecyclerview.util.imageUrl

class FullImage : BaseActivity() {

    val binding : ActivityFullImageBinding by lazy {
        ActivityFullImageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val image = imageUrl
        binding.apply {
            fullImage = image
            back.setOnClickListener { finish() }
        }
    }
}