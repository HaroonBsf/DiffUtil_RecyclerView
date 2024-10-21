package com.example.diffutilsrecyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.diffutilsrecyclerview.model.User

class DataDiffCallback(
    private val oldList: List<User>,
    private val newList: List<User>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}