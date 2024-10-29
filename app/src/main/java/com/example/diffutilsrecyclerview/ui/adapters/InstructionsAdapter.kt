package com.example.diffutilsrecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.databinding.RvRecipeDetailsItemBinding
import javax.inject.Inject

class InstructionsAdapter @Inject constructor() : RecyclerView.Adapter<InstructionsAdapter.ViewHolder>() {

    private var instructions = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvRecipeDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(instructions[position])
    }

    override fun getItemCount(): Int = instructions.size

    inner class ViewHolder(val binding: RvRecipeDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: String) {
            binding.instruction = ingredient
            binding.executePendingBindings()
        }
    }

    fun updateIngredients(newUserList: List<String>) {
        val diffCallback = RecipesInstructionsDiffCallback(instructions, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        instructions = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}

class RecipesInstructionsDiffCallback(private val oldList: List<String>, private val newList: List<String>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

