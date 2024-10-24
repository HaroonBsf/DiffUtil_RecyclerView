package com.example.diffutilsrecyclerview.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.util.Constant
import com.example.diffutilsrecyclerview.util.DataDiffCallback
import com.example.diffutilsrecyclerview.data.models.User
import com.example.diffutilsrecyclerview.databinding.RvItemBinding
import com.example.diffutilsrecyclerview.ui.DetailedActivity
import javax.inject.Inject

class Adapter @Inject constructor() : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var userList = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.userData = currentItem
        holder.binding.executePendingBindings()

        holder.itemView.setOnClickListener {
            Constant.userData = currentItem
            val intent = Intent(holder.itemView.context, DetailedActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateUsers(newUserList: List<User>) {
        val diffCallback = DataDiffCallback(userList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        userList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}