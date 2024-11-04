package com.example.diffutilsrecyclerview.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsrecyclerview.R
import com.example.diffutilsrecyclerview.data.models.remoteDataModels.User
import com.example.diffutilsrecyclerview.databinding.RvUsersItemBinding
import com.example.diffutilsrecyclerview.util.userData
import javax.inject.Inject

class UsersAdapter @Inject constructor() : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var userList = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvUsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.userData = currentItem
        holder.binding.executePendingBindings()

        holder.itemView.setOnClickListener {
            userData = currentItem
            it.findNavController().navigate(R.id.action_homeFragment_to_userDetailFragment)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(val binding: RvUsersItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateUsers(newUserList: List<User>) {
        val diffCallback = UsersDiffCallback(userList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        userList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}

class UsersDiffCallback(private val oldList: List<User>, private val newList: List<User>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}