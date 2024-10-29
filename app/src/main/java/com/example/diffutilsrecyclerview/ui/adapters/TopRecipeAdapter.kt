package com.example.diffutilsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.Result
import com.example.diffutilsrecyclerview.databinding.RvHorizontalRecipeBinding
import javax.inject.Inject

class TopRecipeAdapter @Inject constructor() : RecyclerView.Adapter<TopRecipeAdapter.ViewHolder>() {

    private var recipeList = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvHorizontalRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = recipeList[position]
        holder.binding.topRecipeData = currentItem
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(val binding: RvHorizontalRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateTopRecipes(newUserList: List<Result>) {
        val diffCallback = TopRecipesDiffCallback(recipeList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recipeList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}

class TopRecipesDiffCallback(private val oldList: List<Result>, private val newList: List<Result>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}