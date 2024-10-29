package com.example.diffutilsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.Recipe
import com.example.diffutilsrecyclerview.databinding.RvRecipeDetailsItemBinding
import javax.inject.Inject

class IngredientsAdapter @Inject constructor() : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    private var ingredients = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvRecipeDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size

    inner class ViewHolder(val binding: RvRecipeDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: String) {
            binding.instruction = ingredient
            binding.executePendingBindings()
        }
    }

    fun updateIngredients(newUserList: List<String>) {
        val diffCallback = RecipesIngredientsDiffCallback(ingredients, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        ingredients = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}

class RecipesIngredientsDiffCallback(private val oldList: List<String>, private val newList: List<String>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

