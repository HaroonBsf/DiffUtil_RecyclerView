package com.example.diffutilsrecyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diffutilsrecyclerview.model.User
import de.hdodenhof.circleimageview.CircleImageView

class Adapter :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

        private var userList = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.tvName.text = "${currentItem.firstName} ${currentItem.lastName}"
        holder.tvEmail.text = currentItem.email
        val url = currentItem.image

        Glide
            .with(holder.itemView.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            Constant.userData = currentItem
            val intent = Intent(holder.itemView.context, DetailedActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: CircleImageView
        var tvName: TextView
        var tvEmail: TextView

        init {
            image = itemView.findViewById(R.id.circleImageView)
            tvName = itemView.findViewById(R.id.name)
            tvEmail = itemView.findViewById(R.id.email)
        }
    }

    fun updateUsers(newUserList: List<User>) {
        val diffCallback = DataDiffCallback(userList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        userList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}