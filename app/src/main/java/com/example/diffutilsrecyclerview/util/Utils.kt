package com.example.diffutilsrecyclerview.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.diffutilsrecyclerview.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.placeholder)
        .into(view)
}

@BindingAdapter("intToString")
fun intToString(textView: TextView, text: Int?) {
    val text = "${text.toString()} Years"
    textView.text = text
}

@BindingAdapter("recipeTime")
fun recipeTime(textView: TextView, text: Int?) {
    val text = "${text.toString()} mins"
    textView.text = text
}

@BindingAdapter("doubleToString")
fun doubleToString(textView: TextView, text: Double?) {
    val text = text.toString()
    textView.text = text
}