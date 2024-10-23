package com.example.diffutilsrecyclerview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.diffutilsrecyclerview.util.Constant
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {

    val binding: ActivityDetailedBinding by lazy {
        ActivityDetailedBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val data = Constant.userData
        if (data != null) {
            Toast.makeText(this, "Response Successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Unexpected Error", Toast.LENGTH_SHORT).show()
        }
        val url = data?.image
        binding.apply {
            back.setOnClickListener { finish() }
            Glide
                .with(this@DetailedActivity)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(detailedCircleImage)
            tvDetailedName.text = "${data?.firstName} ${data?.lastName}"
            tvDetailedEmail.text = data?.email
            tvName.text = "${data?.firstName} ${data?.maidenName} ${data?.lastName}"
            tvAge.text = data?.age.toString()
            tvGender.text = data?.gender
            tvPhone.text = data?.phone
            tvUserName.text = data?.username
            tvBirth.text = data?.birthDate
            tvBloodGroup.text = data?.bloodGroup
            tvEyeColor.text = data?.eyeColor
            tvHairColor.text = data?.hair?.color
            tvHairType.text = data?.hair?.type
            tvAddress.text =
                "${data?.address?.address}, ${data?.address?.city}, ${data?.address?.state}, ${data?.address?.country}"
        }
    }
}