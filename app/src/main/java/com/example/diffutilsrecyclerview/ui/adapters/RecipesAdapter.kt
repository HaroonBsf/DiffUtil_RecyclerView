package com.example.diffutilsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.Recipe
import com.example.diffutilsrecyclerview.databinding.RvRecipesItemsBinding
import com.example.diffutilsrecyclerview.util.recipeData
import javax.inject.Inject

class RecipeAdapter @Inject constructor() : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private var recipeList = listOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvRecipesItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = recipeList[position]
        holder.binding.recipeData = currentItem
        holder.binding.executePendingBindings()

        holder.itemView.setOnClickListener {
            recipeData = currentItem
            it.findNavController().navigate(R.id.action_homeFragment_to_recipeDetailFragment)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(val binding: RvRecipesItemsBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateUsers(newUserList: List<Recipe>) {
        val diffCallback = RecipeDiffCallback(recipeList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recipeList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}

class RecipeDiffCallback(private val oldList: List<Recipe>, private val newList: List<Recipe>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
